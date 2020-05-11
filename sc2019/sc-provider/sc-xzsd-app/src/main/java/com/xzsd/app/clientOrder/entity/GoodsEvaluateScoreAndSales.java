package com.xzsd.app.clientOrder.entity;

/**
 * 商品平均评价等级和销售量
 */
public class GoodsEvaluateScoreAndSales {
    /**
     * 商品平均评价等级
     */
    private double goodsEvaluateScore;
    /**
     * 销售量
     */
    private int goodsSales;
    /**
     *商品编号
     */
    private String goodsId;

    public double getGoodsEvaluateScore() {
        return goodsEvaluateScore;
    }

    public void setGoodsEvaluateScore(double goodsEvaluateScore) {
        this.goodsEvaluateScore = goodsEvaluateScore;
    }

    public int getGoodsSales() {
        return goodsSales;
    }

    public void setGoodsSales(int goodsSales) {
        this.goodsSales = goodsSales;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
}
