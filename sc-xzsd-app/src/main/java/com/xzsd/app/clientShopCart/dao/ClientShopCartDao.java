package com.xzsd.app.clientShopCart.dao;

import com.xzsd.app.clientShopCart.entity.ClientShopCartInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ClientShopCartDao {
    /**
     * 新增购物车
     */
    int addShoppingCart(ClientShopCartInfo clientShopCartInfo);
    /**
     * 查询购物车列表
     */
    List<ClientShopCartInfo> listShoppingCarts(ClientShopCartInfo clientShopCartInfo);
    /**
     * 修改购物车商品数量
     */
    int updateShoppingCart(ClientShopCartInfo clientShopCartInfo);
    /**
     * 删除购物车商品
     */
    int deleteShoppingCart(@Param(value = "listId") List<String> listId,@Param(value = "userCode")String userCode);
}
