package com.xzsd.app.clientGoods.dao;

import com.xzsd.app.clientGoods.entity.ClassifyListVO;
import com.xzsd.app.clientGoods.entity.EvaluatesIn;
import com.xzsd.app.clientGoods.entity.EvaluatesOut;
import com.xzsd.app.clientGoods.entity.GetGoodsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientGoodsDao {
    /**
     * 查询一级分类
     */
    List<ClassifyListVO> listOneGoodsClassify();
    /**
     * 查询二级分类和商品
     */
    List<ClassifyListVO> listGetClassGoods(String classifyId);
    /**
     * 查询商品信息详情
     */
    GetGoodsVO getGoods(String goodsId);
    /**
     * 统计浏览量
     */
    void countViewsNum(String goodsId);
    /**
     * 查询门店名称
     */
    String getGoodsStoreName(String userCode);
    /**
     * 查询商品评价列表
     */
    List<EvaluatesOut> listGoodsEvaluatesByPage(EvaluatesIn evaluatesIn);
}
