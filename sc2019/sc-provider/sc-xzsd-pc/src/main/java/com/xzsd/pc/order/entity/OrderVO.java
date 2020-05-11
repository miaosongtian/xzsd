package com.xzsd.pc.order.entity;

import java.util.List;

/**
 * 订单详情返回信息（封装)
 */
public class OrderVO {
    /**
     * 订单详情信息集合
     */
    private List<OrderDetalil> orderDeepenList;

    public List<OrderDetalil> getOrderDeepenList() {
        return orderDeepenList;
    }

    public void setOrderDeepenList(List<OrderDetalil> orderDeepenList) {
        this.orderDeepenList = orderDeepenList;
    }
}
