package com.xzsd.pc.goods.dao;


import com.xzsd.pc.goods.entity.Goods;
import com.xzsd.pc.goods.entity.GoodsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Goodsdao
 * auother:miaosongtian
 * time:2020-3-27
 */
@Mapper
public interface GoodsDao {
    /**
     * 新增商品
     */
    int addGoods(GoodsInfo goodsInfo);
    /**
     * 计算商品书号数量
     */
    int countIsbn(GoodsInfo goodsInfo);
    /**
     * 删除商品
     */
    int deleteGoods(@Param("listId") List<String> listId, @Param("userCode") String userCode);
    /**
     * 修改商品
     */
    int updateGoods(GoodsInfo goodsInfo);
    /**
     * 查询商品详情
     */
    GoodsInfo getGoods(@Param("goodsId") String goodsId);
    /**
     * 分页查询商品列表
     */
    List<GoodsInfo> listGoods(GoodsInfo goodsInfo);
    /**
     * MQ练习
     */
    void mqGoods(GoodsInfo goodsInfo);
    /**
     * 查询商品分类下拉框
     */
    List<String> listGoodsClassify(@Param("classifyId") String classifyId);
    /**
     * 修改商品状态
     */
    int updateGoodsShelfState(@Param("listUpdata") List<Goods> listUpdata);
}
