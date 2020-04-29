package com.xzsd.app.clientOrder.dao;

import com.xzsd.app.clientOrder.entity.ClientOrderInfo;
import com.xzsd.app.clientOrder.entity.ClientOrderOutput;
import com.xzsd.app.clientOrder.entity.EvaluateList;
import com.xzsd.app.clientOrder.entity.GoodsList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClientOrderDao {
    /**
     * 新增订单
     */
    int addOrder(ClientOrderInfo clientOrderInfo);
    /**
     * 新增订单详情（商品信息）
     */
    int addOrderDetail(@Param("listGoodsAdd") List<ClientOrderInfo> listGoodsAdd);
    /**
     * 查询订单列表
     */
    List<ClientOrderOutput> listOrder(ClientOrderInfo clientOrderInfo);
    /**
     * 统计订单中商品总数
     */
    int countGoodsNum(String orderId);
    /**
     * 统计订单总价
     */
    String countAllPrice(String orderId);
    /**
     * 修改订单状态
     */
    int updateOrderState(@Param("listUpdata") List<ClientOrderInfo> listUpdata);
    /**
     * 查询订单详情
     */
    ClientOrderOutput listOrderDeepen(@Param("orderId")String orderId);
    /**
     * 查询订单中商品列表
     */
    List<GoodsList> listGoods (String orderId);
    /**
     * 新增商品评价
     */
    int addGoodsEvaluate(EvaluateList evaluateList);
}
