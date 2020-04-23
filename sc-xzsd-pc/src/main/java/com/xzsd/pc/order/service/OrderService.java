package com.xzsd.pc.order.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.order.dao.OrderDao;
import com.xzsd.pc.order.entity.OrderDetalil;
import com.xzsd.pc.order.entity.OrderInfo;
import com.xzsd.pc.order.entity.OrderState;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {
    @Resource
    private OrderDao orderDao;

    /**
     * 分页查询订单列表
     * author:miaosongtian
     * time:2020-04-15
     */
    public AppResponse listOrders(OrderInfo orderInfo) {
        //当角色为0超级管理员或1管理员时，查询所有数据，角色为2店长时，查店长的数据
        if (orderInfo.getRole().equals("1") || orderInfo.getRole().equals("0")) {
            PageHelper.startPage(orderInfo.getPageNum(), orderInfo.getPageSize());
            List<OrderInfo> orderInfoList = orderDao.listOrders(orderInfo);
            // 包装Page对象
            PageInfo<OrderInfo> pageData = new PageInfo<OrderInfo>(orderInfoList);
            return AppResponse.success("查询成功！", pageData);
        }else if (orderInfo.getRole().equals("2")){
            PageHelper.startPage(orderInfo.getPageNum(), orderInfo.getPageSize());
            List<OrderInfo> orderInfoRole2 = orderDao.listOrdersRole2(orderInfo);
            // 包装Page对象
            PageInfo<OrderInfo> pageData = new PageInfo<OrderInfo>(orderInfoRole2);
            return AppResponse.success("查询成功！", pageData);
        }else return AppResponse.success("角色没有权限访问！");
    }

    /**
     * 查询订单详情
     * author:miaosongtian
     * time:2020-04-16
     */
    public AppResponse getListOrder(String orderId){
        OrderDetalil orderDetalil = orderDao.getListOrder(orderId);
        return AppResponse.success("查询成功！",orderDetalil);
    }

    /**
     * 修改商品状态
     * author:miaosongtian
     * time:2020-04-14
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateOrderState(OrderInfo orderInfo) {
        //把字符串通过逗号分成list
        List<String> listOrderId = Arrays.asList(orderInfo.getOrderId().split(","));
        List<String> listVersion = Arrays.asList(orderInfo.getVersion().split(","));
        //定义一个变量用于存放listOrderId,listVersion
        List<OrderState> listUpdata = new ArrayList<>();
        String orderStateId = orderInfo.getOrderStateId();
        String userCode = orderInfo.getLastModifiedBy();
        for (int i = 0;i < listOrderId.size();i++){
            OrderState orderState = new OrderState();
            orderState.setOrderStateId(orderStateId);
            orderState.setUserCode(userCode);
            orderState.setVersion(listVersion.get(i));
            orderState.setOrderId(listOrderId.get(i));
            listUpdata.add(orderState);
        }
        AppResponse appResponse = AppResponse.success("状态修改成功！");
        int count = orderDao.updateOrderState(listUpdata);
        if(0 == count) {
            appResponse = AppResponse.bizError("状态修改失败，请重试！");
        }
        return appResponse;
    }
}
