package com.xzsd.app.clientHome.entity;

public class ClientHomeInfo {
    /**
     * 轮播图图片路径
     */
    private String slideshowPath;
    /**
     * 商品编号
     */
    private String goodsId;

    public String getSlideshowPath() {
        return slideshowPath;
    }

    public void setSlideshowPath(String slideshowPath) {
        this.slideshowPath = slideshowPath;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
}
