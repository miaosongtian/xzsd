package com.xzsd.app.clientShopCart.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.clientShopCart.entity.ClientShopCartInfo;
import com.xzsd.app.clientShopCart.service.ClientShopCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("clientShopCart")
public class ClientShopCartController {
    private static final Logger logger = LoggerFactory.getLogger(ClientShopCartController.class);
    @Resource
    private ClientShopCartService clientShopCartService;

    /**
     * 新增购物车
     * author:miaosongtian
     * time:2020-4-26
     */
    @PostMapping("addShoppingCart")
    public AppResponse addShoppingCart(ClientShopCartInfo clientShopCartInfo) {
        try {
            //获取当前登录用户id
            String userCode = SecurityUtils.getCurrentUserId();
            clientShopCartInfo.setCreateBy(userCode);
            return clientShopCartService.addShoppingCart(clientShopCartInfo);
        } catch (Exception e) {
            logger.error("新增购物车错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询购物车列表
     * author:miaosongtian
     * time:2020-4-26
     */
    @PostMapping("listShoppingCarts")
    public AppResponse listShoppingCarts(ClientShopCartInfo clientShopCartInfo) {
        try {
            //获取当前登录用户id
            String userCode = SecurityUtils.getCurrentUserId();
            clientShopCartInfo.setCreateBy(userCode);
            return clientShopCartService.listShoppingCarts(clientShopCartInfo);
        } catch (Exception e) {
            logger.error("查询购物车列表错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改购物车商品数量
     * author:miaosongtian
     * time:2020-4-26
     */
    @PostMapping("updateShoppingCart")
    public AppResponse updateShoppingCart(ClientShopCartInfo clientShopCartInfo) {
        try {
            //获取当前登录用户id
            String userCode = SecurityUtils.getCurrentUserId();
            clientShopCartInfo.setLastModifiedBy(userCode);
            return clientShopCartService.updateShoppingCart(clientShopCartInfo);
        } catch (Exception e) {
            logger.error("修改购物车商品数量错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除购物车商品
     * author:miaosongtian
     * time:2020-4-26
     */
    @PostMapping("deleteShoppingCart")
    public AppResponse deleteShoppingCart(String shopCartId) {
        try {
            //获取当前登录用户id
            String userCode = SecurityUtils.getCurrentUserId();
            return clientShopCartService.deleteShoppingCart(shopCartId,userCode);
        } catch (Exception e) {
            logger.error("删除购物车商品错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
