package com.xzsd.app.clientGoods.entity;

import java.util.List;

public class ClientGoodsInfo {
    /**
     *一级分类信息集合
     */
    private List<ClassifyListVO> oneClassifyList;
    /**
     * 二级分类信息集合
     */
    private List<ClassifyListVO> twoClassifyList;

    public List<ClassifyListVO> getOneClassifyList() {
        return oneClassifyList;
    }

    public void setOneClassifyList(List<ClassifyListVO> oneClassifyList) {
        this.oneClassifyList = oneClassifyList;
    }

    public List<ClassifyListVO> getTwoClassifyList() {
        return twoClassifyList;
    }

    public void setTwoClassifyList(List<ClassifyListVO> twoClassifyList) {
        this.twoClassifyList = twoClassifyList;
    }
}
