package com.xzsd.pc.goodsClassify.controller;




import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.AuthUtils;

import com.xzsd.pc.goodsClassify.entity.GoodsClassifyInfo;
import com.xzsd.pc.goodsClassify.service.GoodsClassifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("goodsClassify")
public class GoodsClassifyController {
    private static final Logger logger = LoggerFactory.getLogger(GoodsClassifyController.class);
    @Resource
    private GoodsClassifyService goodsClassifyService;

    /**
     *  新增商品分类
     * author:miaosongtian
     * time:2020-04-9
     */
    @PostMapping("addGoodsClassify")
    public AppResponse addGoodsClassify(GoodsClassifyInfo goodsClassifyInfo) {
        try {
            //获取用户id
            String userId =  SecurityUtils.getCurrentUserId();
            goodsClassifyInfo.setCreateBy(userId);
            AppResponse appResponse = goodsClassifyService.addGoodsClassify(goodsClassifyInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("商品分类新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除商品分类
     * author:miaosongtian
     * time:2020-04-9
     */
    @PostMapping("deleteGoodsClassify")
    public AppResponse deleteGoodsClassify (GoodsClassifyInfo goodsClassifyInfo){
        try{
            //删除商品分类
            String userId =  SecurityUtils.getCurrentUserId();
            goodsClassifyInfo.setLastModifiedBy(userId);
            return goodsClassifyService.deleteGoodsClassify(goodsClassifyInfo);
        }catch (Exception e) {
            logger.error("商品分类删除错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改商品分类
     * author:miaosongtian
     * time:2020-04-9
     */
    @PostMapping("updateGoodsClassify")
    public AppResponse updateGoodsClassify (GoodsClassifyInfo goodsClassifyInfo){
        try{
            //修改商品分类
            String userId =  SecurityUtils.getCurrentUserId();
            goodsClassifyInfo.setLastModifiedBy(userId);
            return goodsClassifyService.updateGoodsClassify(goodsClassifyInfo);
        }catch (Exception e) {
            logger.error("商品分类修改错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询商品分类详情
     * author:miaosongtian
     * time:2020-04-9
     */
    @PostMapping("getGoodsClassify")
    public AppResponse getGoodsClassify (String classifyId){
        try{
            //查询商品分类详情
            return goodsClassifyService.getGoodsClassify(classifyId);
        }catch (Exception e) {
            logger.error("商品分类查询详情错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询商品分类列表
     * author:miaosongtian
     * time:2020-04-9
     */
    @PostMapping("listAllGoodsClassify")
    public AppResponse listAllGoodsClassify(){
        try {
            return goodsClassifyService.listAllGoodsClassify();
        } catch (Exception e) {
            logger.error("查询商品分类列表异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}

