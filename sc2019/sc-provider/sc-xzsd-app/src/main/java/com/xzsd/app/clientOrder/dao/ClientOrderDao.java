package com.xzsd.app.clientOrder.dao;

import com.xzsd.app.clientOrder.entity.*;
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
     * 修改订单状态
     */
    int updateOrderState(ClientOrderInfo clientOrderInfo);
    /**
     * 取消订单后删除订单详情中的商品
     */
    void deleteOrderGoods(ClientOrderInfo clientOrderInfo);
    /**
     * 评价完成后修改订单状态为5
     */
    void updateOrderStateTo5(ClientOrderInfo clientOrderInfo);
    /**
     * 设置商品销售量和平均评价等级
     */
    void setGoodsSalesAndEvaluate(@Param("listSet") List<GoodsEvaluateScoreAndSales> listSet);
    /**
     * 获取当前平均评价等级和销售量
     */
    List<GoodsEvaluateScoreAndSales> getGoodsEvaluateScoreAndSales(String orderId);
    /**
     * 设置更新后的库存
     */
    void setGoodsInventory(@Param("listSet") List<ClientOrderInfo> listSet);
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
    int addGoodsEvaluate(@Param("listEvaluateAdd") List<EvaluateList> listEvaluateAdd);
    /**
     * 查询订单评价商品信息列表
     */
    List<GoodsList> listGoodsForEvaluate(@Param("orderId")String orderId);
}
