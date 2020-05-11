package com.xzsd.app.clientGoods.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.clientGoods.entity.EvaluatesIn;
import com.xzsd.app.clientGoods.service.ClientGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("clientGoods")
public class ClientGoodsController {
    private static final Logger logger = LoggerFactory.getLogger(ClientGoodsController.class);
    @Resource
    private ClientGoodsService clientGoodsService;

    /**
     * 查询一级分类
     * author:miaosongtian
     * time:2020-4-24
     */
    @PostMapping("listOneGoodsClassify")
    public AppResponse listOneGoodsClassify(){
        try{
            return clientGoodsService.listOneGoodsClassify();
        }catch (Exception e) {
            logger.error("一级分类查询错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询二级分类和商品
     * author:miaosongtian
     * time:2020-4-24
     */
    @PostMapping("listGetClassGoods")
    public AppResponse listGetClassGoods(String classifyId){
        try{
            return clientGoodsService.listGetClassGoods(classifyId);
        } catch (Exception e){
            logger.error("二级分类和商品查询错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询商品信息详情
     * author:miaosongtian
     * time:2020-4-25
     */
    @PostMapping("getGoods")
    public AppResponse getGoods(String goodsId){
        try{
            return clientGoodsService.getGoods(goodsId);
        }catch(Exception e){
            logger.error("查询商品信息详情错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询商品评价列表
     * author:miaosongtian
     * time:2020-4-25
     */
    @PostMapping("listGoodsEvaluates")
    public AppResponse listGoodsEvaluates(EvaluatesIn evaluatesIn){
        try{
            return clientGoodsService.listGoodsEvaluates(evaluatesIn);
        }catch(Exception e){
            logger.error("分页查询商品评价错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
