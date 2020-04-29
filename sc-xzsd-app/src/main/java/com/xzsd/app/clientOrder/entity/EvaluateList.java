package com.xzsd.app.clientOrder.entity;

import java.util.List;

public class EvaluateList {
    /**
     * 商品编号
     */
    private String goodsId;
    /**
     * 评价内容
     */
    private String evaluateContent;
    /**
     * 评价等级
     */
    private int evaluateScore;
    /**
     * 评价图片
     */
    private List<ImageList> imageList;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public int getEvaluateScore() {
        return evaluateScore;
    }

    public void setEvaluateScore(int evaluateScore) {
        this.evaluateScore = evaluateScore;
    }

    public List<com.xzsd.app.clientOrder.entity.ImageList> getImageList() {
        return imageList;
    }

    public void setImageList(List<com.xzsd.app.clientOrder.entity.ImageList> imageList) {
        this.imageList = imageList;
    }
}
