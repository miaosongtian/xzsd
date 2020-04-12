package com.xzsd.pc.goodsClassify.dao;



import com.xzsd.pc.goodsClassify.entity.GoodsClassifyInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Goodsdao
 * auother:miaosongtian
 * time:2020-3-27
 */
@Mapper
public interface GoodsClassifyDao {

    /**
     * 新增商品分类
     */
    int addGoodsClassify(GoodsClassifyInfo goodsClassifyInfo);
    /**
     * 删除商品分类
     */
    int deleteGoodsClassify(@Param("classifyId") String classifyId,@Param("userId") String userId);
    /**
     * 修改商品分类
     */
    int updateGoodsClassify(GoodsClassifyInfo goodsClassifyInfo);
    /**
     * 查询商品分类详情
     */
    GoodsClassifyInfo getGoodsClassify(@Param("classifyId") String classifyId);
    /**
     * 查询商品分类列表
     */
    List<GoodsClassifyInfo> listAllGoodsClassify();
//    /**
//     * MQ练习
//     */
//    void mqGoods(GoodsInfo goodsInfo);
//    /**
//     * 查询商品分类下拉框
//     */
//    List<String> listGoodsClassify(@Param("classfyId") String classfyId) ;
}
