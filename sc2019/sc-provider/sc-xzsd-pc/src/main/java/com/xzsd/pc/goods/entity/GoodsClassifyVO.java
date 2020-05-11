package com.xzsd.pc.goods.entity;

import java.util.List;

public class GoodsClassifyVO {
    /**
     * 商品分类信息集合
     */
    private List<GoodsInfo> goodsClassifyList;

    public List<GoodsInfo> getGoodsClassifyList() {
        return goodsClassifyList;
    }

    public void setGoodsClassifyList(List<GoodsInfo> goodsClassifyList) {
        this.goodsClassifyList = goodsClassifyList;
    }
}
