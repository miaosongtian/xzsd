package com.xzsd.app.managerOrder.dao;

import com.xzsd.app.clientOrder.entity.ClientOrderInfo;
import com.xzsd.app.clientOrder.entity.ClientOrderOutput;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ManagerOrderDao {
    /**
     * 查询订单列表
     */
    List<ClientOrderOutput> listManagerOrders(ClientOrderInfo clientOrderInfo);
    /**
     * 修改订单状态
     */
    int updateManagerOrderState(ClientOrderInfo clientOrderInfo);
    /**
     * 查询订单详情
     */
    ClientOrderOutput listManagerOrderDeepen(@Param("orderId")String orderId);
}
