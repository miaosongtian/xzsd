package com.xzsd.pc.hotGoods.controller;


import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.goods.entity.GoodsInfo;
import com.xzsd.pc.hotGoods.entity.HotGoodsInfo;
import com.xzsd.pc.hotGoods.service.HotGoodsService;
import com.xzsd.pc.slideshowHome.service.SlideshowHomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("hotGoods")
public class HotGoodsController {
    private static final Logger logger = LoggerFactory.getLogger(HotGoodsController.class);
    @Resource
    private HotGoodsService hotGoodsService;
    @Resource
    private SlideshowHomeService slideshowHomeService;

    /**
     *  新增热门商品
     * author: miaosongtian
     * time:2020-04-16
     */
    @PostMapping("addHotGoods")
    public AppResponse addHotGoods(HotGoodsInfo hotGoodsInfo) {
        try {
            //获取用户id
            String userCode =  SecurityUtils.getCurrentUserId();
            hotGoodsInfo.setCreateBy(userCode);
            AppResponse appResponse = hotGoodsService.addHotGoods(hotGoodsInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("热门商品新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除热门商品
     * author:miaosongtian
     * time:2020-04-16
     */
    @PostMapping("deleteHotGoods")
    public AppResponse deleteHotGoods (String hotGoodsId){
        try{
            //删除热门商品
            String userCode =  SecurityUtils.getCurrentUserId();
            HotGoodsInfo hotGoodsInfo = new HotGoodsInfo();
            hotGoodsInfo.setLastModifiedBy(userCode);
            return hotGoodsService.deleteHotGoods(hotGoodsId,userCode);
        }catch (Exception e) {
            logger.error("热门商品删除错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改热门商品
     * author:miaosongtian
     * time:2020-04-16
     */
    @PostMapping("updateHotGoods")
    public AppResponse updateHotGoods (HotGoodsInfo hotGoodsInfo){
        try{
            //修改热门商品
            String userCode =  SecurityUtils.getCurrentUserId();
            hotGoodsInfo.setLastModifiedBy(userCode);
            return hotGoodsService.updateHotGoods(hotGoodsInfo);
        }catch (Exception e) {
            logger.error("热门商品修改错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询热门商品详情
     * author:miaosongtian
     * time:2020-4-16
     */
    @PostMapping("getHotGoods")
    public AppResponse getHotGoods (String hotGoodsId){
        try{
            //查询热门商品详情
            return hotGoodsService.getHotGoods(hotGoodsId);
        }catch (Exception e) {
            logger.error("热门商品查询详情错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 分页查询热门商品列表
     * author:miaosongtian
     * time:2020-4-16
     */
    @PostMapping("listHotGoods")
    public AppResponse listSlideshowHome(HotGoodsInfo hotGoodsInfo){
        try {
            return hotGoodsService.listHotGoods(hotGoodsInfo);
        } catch (Exception e) {
            logger.error("查询热门商品列表异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 分页查询商品列表
     * author:miaosongtian
     * time:2020-4-16
     */
    @PostMapping("listGoods")
    public AppResponse listGoods(GoodsInfo goodsInfo){
        try {
            return slideshowHomeService.listGoods(goodsInfo);
        } catch (Exception e) {
            logger.error("查询商品列表异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询热门商品展示数量
     * author:miaosongtian
     * time:2020-4-17
     */
    @PostMapping("getHotGoodsShowNum")
    public AppResponse getHotGoodsShowNum(){
        try {
            return hotGoodsService.getHotGoodsShowNum();
        } catch (Exception e) {
            logger.error("查询商品展示数量异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改热门商品展示数量
     * author:miaosongtian
     * time:2020-4-17
     */
    @PostMapping("updateHotGoodsShowNum")
    public AppResponse updateHotGoodsShowNum(HotGoodsInfo hotGoodsInfo){
        try {
            String userCode =  SecurityUtils.getCurrentUserId();
            hotGoodsInfo.setLastModifiedBy(userCode);
            return hotGoodsService.updateHotGoodsShowNum(hotGoodsInfo);
        } catch (Exception e) {
            logger.error("修改商品展示数量异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
