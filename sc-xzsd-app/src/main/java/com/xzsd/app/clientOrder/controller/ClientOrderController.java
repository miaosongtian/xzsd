package com.xzsd.app.clientOrder.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.clientOrder.entity.ClientOrderInfo;
import com.xzsd.app.clientOrder.service.ClientOrderService;
import com.xzsd.app.clientShopCart.controller.ClientShopCartController;
import com.xzsd.app.clientShopCart.entity.ClientShopCartInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("clientOrder")
public class ClientOrderController {
    private static final Logger logger = LoggerFactory.getLogger(ClientShopCartController.class);
    @Resource
    private ClientOrderService clientOrderService;

    /**
     * 新增订单
     * author:miaosongtian
     * time:2020-4-26
     */
    @PostMapping("addOrder")
    public AppResponse addOrder(ClientOrderInfo clientOrderInfo) {
        try {
            //获取当前登录用户id
            String userCode = SecurityUtils.getCurrentUserId();
            clientOrderInfo.setCreateBy(userCode);
            return clientOrderService.addOrder(clientOrderInfo);
        } catch (Exception e) {
            logger.error("新增订单错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询订单列表
     * author:miaosongtian
     * time:2020-4-27
     */
    @PostMapping("listOrder")
    public AppResponse listOrder(String orderStateId) {
        try {
            //获取当前登录用户id
            String userCode = SecurityUtils.getCurrentUserId();
            return clientOrderService.listOrder(orderStateId, userCode);
        } catch (Exception e) {
            logger.error("查询订单列表错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改订单状态
     * author:miaosongtian
     * time:2020-4-28
     */
    @PostMapping("updateOrderState")
    public AppResponse updateOrderState (ClientOrderInfo clientOrderInfo){
        try{
            //修改订单状态
            String userCode = SecurityUtils.getCurrentUserId();
            clientOrderInfo.setLastModifiedBy(userCode);
            return clientOrderService.updateOrderState(clientOrderInfo);
        }catch (Exception e) {
            logger.error("订单状态修改错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询订单详情
     * author:miaosongtian
     * time:2020-4-28
     */
    @PostMapping("listOrderDeepen")
    public AppResponse listOrderDeepen (String orderId){
        try{
            return clientOrderService.listOrderDeepen(orderId);
        }catch (Exception e) {
            logger.error("查询订单详情错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 新增订单商品评价
     * author:miaosongtian
     * time:2020-4-28
     */
    @PostMapping("addGoodsEvaluate")
    public AppResponse addGoodsEvaluate(ClientOrderInfo clientOrderInfo) {
        try {
            //获取当前登录用户id
            String userCode = SecurityUtils.getCurrentUserId();
            clientOrderInfo.setCreateBy(userCode);
            return clientOrderService.addGoodsEvaluate(clientOrderInfo);
        } catch (Exception e) {
            logger.error("新增商品评价错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
