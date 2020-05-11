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
     * 分页查询订单列表（管理员）
     */
    List<OrderInfo> listOrders(OrderInfo orderInfo);
    /**
     * 分页查询订单列表（店长）
     */
    List<OrderInfo> listOrdersRole2(OrderInfo orderInfo);
    /**
     * 查询订单商品详情
     */
    List<OrderDetalil> getListOrder(@Param("orderId") String orderId);
    /**
     * 修改商品状态
     */
    int updateOrderState(@Param("listUpdata") List<OrderState> listUpdata);
}
