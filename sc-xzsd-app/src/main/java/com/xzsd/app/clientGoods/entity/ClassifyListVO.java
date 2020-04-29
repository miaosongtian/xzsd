package com.xzsd.app.clientGoods.entity;

import java.util.List;

public class ClassifyListVO {
    /**
     * 分类编号
     */
    private String classifyId;
    /**
     * 分类名称
     */
    private String classifyName;
    /**
     * 二级分类信息集合
     */
    private List<GoodsListVO> goodsList;

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public List<GoodsListVO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsListVO> goodsList) {
        this.goodsList = goodsList;
    }
}
