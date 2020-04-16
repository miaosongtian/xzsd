package com.xzsd.pc.goods.entity;


/**
 * 批量修改商品状态（参数）
 * author:miaosongtian
 * time:2020-4-14
 */
public class Goods {
    /**
     * 商品id
     */
    private String goodsId;
    /**
     * 商品版本号
     */
    private String version;
    /**
     * 商品状态
     */
    private String goodsStateId;
    /**
     * 修改人
     */
    private String userCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getGoodsStateId() {
        return goodsStateId;
    }

    public void setGoodsStateId(String goodsStateId) {
        this.goodsStateId = goodsStateId;
    }
}
