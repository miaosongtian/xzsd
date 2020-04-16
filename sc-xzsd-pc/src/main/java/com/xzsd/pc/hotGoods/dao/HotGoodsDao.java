package com.xzsd.pc.hotGoods.dao;

import com.xzsd.pc.hotGoods.entity.HotGoodsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HotGoodsDao {
    /**
     * 新增热门商品
     */
    int addHotGoods(HotGoodsInfo hotGoodsInfo);
    /**
     * 判断轮播图商品是否存在
     */
    int countGoodsId(HotGoodsInfo hotGoodsInfo);
    /**
     * 判断轮播图序号是否存在
     */
    int countSort(HotGoodsInfo hotGoodsInfo);
    /**
     * 删除热门商品
     */
    int deleteHotGoods(@Param("listId") List<String> listId,@Param("userCode") String userCode);
    /**
     * 修改热门商品
     */
    int updateHotGoods(HotGoodsInfo hotGoodsInfo);
    /**
     * 查询热门商品详情
     */
    HotGoodsInfo getHotGoods(@Param("hotGoodsId") String hotGoodsId);
    /**
     * 分页查询热门商品列表
     */
    List<HotGoodsInfo> listHotGoods(HotGoodsInfo hotGoodsInfo);
}
