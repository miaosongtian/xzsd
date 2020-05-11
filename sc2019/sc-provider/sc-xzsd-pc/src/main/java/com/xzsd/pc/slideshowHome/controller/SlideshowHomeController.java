package com.xzsd.pc.slideshowHome.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.goods.controller.GoodsController;
import com.xzsd.pc.goods.entity.GoodsInfo;
import com.xzsd.pc.slideshowHome.entity.SlideshowHomeInfo;
import com.xzsd.pc.slideshowHome.service.SlideshowHomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("slideshowHome")
public class SlideshowHomeController {
    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);
    @Resource
    private SlideshowHomeService slideshowHomesService;
    /**
     *  新增商品轮播图
     * @author miaosongtian
     * @Date 2020-04-13
     */
    @PostMapping("addSlideshowHome")
    public AppResponse addSlideshowHome(SlideshowHomeInfo slideshowHomeInfo) {
        try {
            //获取用户id
            String userCode =  SecurityUtils.getCurrentUserId();
            slideshowHomeInfo.setCreateBy(userCode);
            AppResponse appResponse = slideshowHomesService.addSlideshowHome(slideshowHomeInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("轮播图新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除轮播图
     * author:miaosongtian
     * time:2020-4-13
     */
    @PostMapping("deleteSlideshowHome")
    public AppResponse deleteSlideshowHome (String slideshowId){
        try{
            //删除商品
            String userCode =  SecurityUtils.getCurrentUserId();
            SlideshowHomeInfo slideshowHomeInfo = new SlideshowHomeInfo();
            slideshowHomeInfo.setLastModifiedBy(userCode);
            return slideshowHomesService.deleteSlideshowHome(slideshowId,userCode);
        }catch (Exception e) {
            logger.error("轮播图删除错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 分页查询轮播图
     * author:miaosongtian
     * time:2020-4-13
     */
    @PostMapping("listSlideshowHome")
    public AppResponse listSlideshowHome(SlideshowHomeInfo slideshowHomeInfo){
        try {
            return slideshowHomesService.listSlideshowHome(slideshowHomeInfo);
        } catch (Exception e) {
            logger.error("查询轮播图列表异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 分页查询商品列表
     * author:miaosongtian
     * time:2020-4-13
     */
    @PostMapping("listGoods")
    public AppResponse listGoods(GoodsInfo goodsInfo){
        try {
            return slideshowHomesService.listGoods(goodsInfo);
        } catch (Exception e) {
            logger.error("查询商品列表异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 批量修改轮播图状态
     * author:miaosongtian
     * time:2020-4-15
     */
    @PostMapping("updateSlideshowHomeState")
    public AppResponse updateSlideshowHomeState (SlideshowHomeInfo slideshowHomeInfo){
        try{
            //修改轮播图状态
            String userCode =  SecurityUtils.getCurrentUserId();
            slideshowHomeInfo.setLastModifiedBy(userCode);
            return slideshowHomesService.updateSlideshowHomeState(slideshowHomeInfo);
        }catch (Exception e) {
            logger.error("轮播图状态修改错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
