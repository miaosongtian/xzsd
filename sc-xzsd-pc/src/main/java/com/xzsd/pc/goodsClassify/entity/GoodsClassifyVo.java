package com.xzsd.pc.goodsClassify.entity;

import java.util.List;

public class GoodsClassifyVo {
    /**
     * 一级分类信息集合
     */
    private List<GoodsClassifyVo1> oneClassifyList;

    public List<GoodsClassifyVo1> getOneClassifyList() {
        return oneClassifyList;
    }

    public void setOneClassifyList(List<GoodsClassifyVo1> oneClassifyList) {
        this.oneClassifyList = oneClassifyList;
    }
}
