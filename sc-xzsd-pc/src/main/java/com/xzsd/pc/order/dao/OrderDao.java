package com.xzsd.pc.order.dao;

import com.xzsd.pc.order.entity.OrderDetalil;
import com.xzsd.pc.order.entity.OrderInfo;
import com.xzsd.pc.order.entity.OrderState;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDao {
    /**
     * 分页查询订单列表
     */
    List<OrderInfo> listOrders(OrderInfo orderInfo);
    /**
     * 查询商品详情
     */
    OrderDetalil getListOrder(@Param("orderId") String orderId);
    /**
     * 修改商品状态
     */
    int updateOrderState(@Param("listUpdata") List<OrderState> listUpdata);
}