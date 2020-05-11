package com.xzsd.pc.goodsClassify.dao;



import com.xzsd.pc.goodsClassify.entity.GoodsClassifyInfo;
import com.xzsd.pc.goodsClassify.entity.GoodsClassifyVo1;
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
    int deleteGoodsClassify(GoodsClassifyInfo goodsClassifyInfo);
    /**
     * 统计一级分类下二级分类数量
     */
    int countTwoClassify(String classifyParent);
    /**
     * 统计二级分类下商品数量
     */
    int countGoods(String classifyId);
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
    List<GoodsClassifyVo1> listAllGoodsClassify();
}
