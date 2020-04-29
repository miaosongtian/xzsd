package com.xzsd.app.clientOrder.entity;

import java.util.List;

/**
 * 订单响应参数
 */
public class ClientOrderOutput {
    /**
     * 门店名称
     */
    private String storeName;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 订单总价
     */
    private String orderAllCost;
    /**
     * 订单商品总数
     */
    private int orderAllGoodsCount;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 创建时间
     */
    private String crateTime;
    /**
     * 订单状态
     */
    private String orderStateId;
    /**
     * 订单中商品信息集合
     */
    private List<GoodsList> goodsList;
    /**
     * 版本号
     */
    private String version;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderAllCost() {
        return orderAllCost;
    }

    public void setOrderAllCost(String orderAllCost) {
        this.orderAllCost = orderAllCost;
    }

    public int getOrderAllGoodsCount() {
        return orderAllGoodsCount;
    }

    public void setOrderAllGoodsCount(int orderAllGoodsCount) {
        this.orderAllGoodsCount = orderAllGoodsCount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(String crateTime) {
        this.crateTime = crateTime;
    }

    public String getOrderStateId() {
        return orderStateId;
    }

    public void setOrderStateId(String orderStateId) {
        this.orderStateId = orderStateId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<GoodsList> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsList> goodsList) {
        this.goodsList = goodsList;
    }
}
