package com.xzsd.app.managerOrder.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.clientOrder.entity.ClientOrderInfo;
import com.xzsd.app.managerOrder.service.ManagerOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("managerOrder")
public class ManagerOrderController {
    private static final Logger logger = LoggerFactory.getLogger(ManagerOrderController.class);
    @Resource
    private ManagerOrderService managerOrderService;

    /**
     * 查询订单列表
     * author:miaosongtian
     * time:2020-4-29
     */
    @PostMapping("listManagerOrders")
    public AppResponse listManagerOrders(String orderStateId) {
        try {
            //获取当前登录用户id
            String userCode = SecurityUtils.getCurrentUserId();
            return managerOrderService.listManagerOrders(orderStateId, userCode);
        } catch (Exception e) {
            logger.error("查询订单列表错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改订单状态
     * author:miaosongtian
     * time:2020-4-29
     */
    @PostMapping("updateManagerOrderState")
    public AppResponse updateManagerOrderState (ClientOrderInfo clientOrderInfo){
        try{
            //修改订单状态
            String userCode = SecurityUtils.getCurrentUserId();
            clientOrderInfo.setLastModifiedBy(userCode);
            return managerOrderService.updateManagerOrderState(clientOrderInfo);
        }catch (Exception e) {
            logger.error("订单状态修改错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询订单详情
     * author:miaosongtian
     * time:2020-4-29
     */
    @PostMapping("listManagerOrderDeepen")
    public AppResponse listManagerOrderDeepen (String orderId){
        try{
            return managerOrderService.listManagerOrderDeepen(orderId);
        }catch (Exception e) {
            logger.error("查询订单详情错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
