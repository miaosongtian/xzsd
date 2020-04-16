package com.xzsd.pc.order.entity;

/**
 * 批量修改订单状态参数
 * author:miaosongtian
 * time:2020-4-16
 */
public class OrderState {
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 版本号
     */
    private String version;
    /**
     * 订单状态
     */
    private String orderStateId;
    /**
     * 修改人
     */
    private String userCode;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOrderStateId() {
        return orderStateId;
    }

    public void setOrderStateId(String orderStateId) {
        this.orderStateId = orderStateId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
