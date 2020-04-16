package com.xzsd.pc.order.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.order.entity.OrderInfo;
import com.xzsd.pc.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Resource
    private OrderService orderServoce;

    /**
     * 分页查询订单列表
     * author:miaosongtian
     * time:2020-4-15
     */
    @PostMapping("listOrders")
    public AppResponse listOrders(OrderInfo orderInfo){
        try {
            return orderServoce.listOrders(orderInfo);
        } catch (Exception e) {
            logger.error("查询订单列表异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询订单详情
     * author:miaosongtian
     * time:2020-04-16
     */
    @PostMapping("getListOrder")
    public AppResponse getListOrder (String orderId){
        try{
            //查询订单详情
            return orderServoce.getListOrder(orderId);
        }catch (Exception e) {
            logger.error("订单查询详情错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 批量修改订单状态
     * author:miaosongtian
     * time:2020-4-16
     */
    @PostMapping("updateOrderState")
    public AppResponse updateOrderState (OrderInfo orderInfo){
        try{
            //修改订单状态
            String userCode =  SecurityUtils.getCurrentUserId();
            orderInfo.setLastModifiedBy(userCode);
            return orderServoce.updateOrderState(orderInfo);
        }catch (Exception e) {
            logger.error("订单状态修改错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
