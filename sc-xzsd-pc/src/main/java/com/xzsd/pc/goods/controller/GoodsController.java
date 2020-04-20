package com.xzsd.pc.goods.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.goods.entity.GoodsInfo;
import com.xzsd.pc.goods.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@RequestMapping("goods")
public class GoodsController {
    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Resource
    private GoodsService goodsService;
    /**
     *  新增商品
     * @author miao
     * @Date 2020-03-27
     */
    @PostMapping("addGoods")
    public AppResponse addGoods(GoodsInfo goodsInfo) {
        try {
            //获取用户id
            String userCode =  SecurityUtils.getCurrentUserId();
            goodsInfo.setCreateBy(userCode);
            AppResponse appResponse = goodsService.addGoods(goodsInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("商品新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除商品
     * author:miaosongtian
     * time:2020-03-28
     */
    @PostMapping("deleteGoods")
    public AppResponse deleteGoods (String goodsId){
        try{
            //删除商品
            String userCode =  SecurityUtils.getCurrentUserId();
            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.setLastModifiedBy(userCode);
            return goodsService.deleteGoods(goodsId,userCode);
        }catch (Exception e) {
            logger.error("商品删除错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改商品
     * author:miaosongtian
     * time:2020-03-29
     */
    @PostMapping("updateGoods")
    public AppResponse updateGoods (GoodsInfo goodsInfo){
        try{
            //更改商品
            String userCode =  SecurityUtils.getCurrentUserId();
            goodsInfo.setLastModifiedBy(userCode);
            return goodsService.updateGoods(goodsInfo);
        }catch (Exception e) {
            logger.error("商品修改错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询商品详情
     * author:miaosongtian
     * time:2020-03-29
     */
    @PostMapping("getGoods")
    public AppResponse getGoods (String goodsId){
        try{
            //查询商品详情
            return goodsService.getGoods(goodsId);
        }catch (Exception e) {
            logger.error("商品查询详情错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 分页查询商品列表
     * author:miaosongtian
     * time:2020-03-29
     */
    @PostMapping("listGoods")
    public AppResponse listGoods(GoodsInfo goodsInfo){
        try {
            return goodsService.listGoods(goodsInfo);
        } catch (Exception e) {
            logger.error("查询商品列表异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询商品分类下拉框
     * author:miaosongtian
     * time:2020-4-9
     */
    @PostMapping("listGoodsClassify")
    public AppResponse listGoodsClassify(String classifyId){
        try {
            return goodsService.listGoodsClassify(classifyId);
        } catch (Exception e) {
            logger.error("查询商品分类下拉框异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 批量修改商品状态
     * author:miaosongtian
     * time:2020-4-14
     */
    @PostMapping("updateGoodsShelfState")
    public AppResponse updateGoodsShelfState (GoodsInfo goodsInfo){
        try{
            //修改商品状态
            String userCode =  SecurityUtils.getCurrentUserId();
            goodsInfo.setLastModifiedBy(userCode);
            return goodsService.updateGoodsShelfState(goodsInfo);
        }catch (Exception e) {
            logger.error("商品状态修改错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}

