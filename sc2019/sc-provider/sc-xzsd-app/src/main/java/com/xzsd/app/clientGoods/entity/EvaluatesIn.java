package com.xzsd.app.clientGoods.entity;

import java.util.List;

public class EvaluatesIn {
    /**
     * 商品编号
     */
    private String goodsId;
    /**
     * 商品评价等级
     */
    private String evaluateScore;
    /**
     * 一页几条
     */
    private int pageSize;
//    /**
//     * 第几页
//     */
    private int pageNum;

//响应参数
    /**
     * 用户账号
     */
    private String userAcct;
    /**
     * 评价内容
     */
    private String evaluateContent;
//    /**
//     * 评价等级
//     */
//    private int evaluateScore;
    /**
     * 评价时间
     */
    private String createTime;
    /**
     * 评价图片路径list
     */
    private List<String> imageList;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getEvaluateScore() {
        return evaluateScore;
    }

    public void setEvaluateScore(String evaluateScore) {
        this.evaluateScore = evaluateScore;
    }

    public String getUserAcct() {
        return userAcct;
    }

    public void setUserAcct(String userAcct) {
        this.userAcct = userAcct;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
