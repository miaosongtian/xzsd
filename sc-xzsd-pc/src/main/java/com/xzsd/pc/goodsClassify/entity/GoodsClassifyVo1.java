package com.xzsd.pc.goodsClassify.entity;

import java.util.List;

public class GoodsClassifyVo1 {
    /**
     * 商品分类名称
     */
    private String classifyName;
    /**
     * 商品分类id
     */
    private String classifyId;
    /**
     * 版本号
     */
    private String version;
    /**
     * 二级分类信息集合
     */
    private List<GoodsClassifyVo2> twoClassifyList;

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<GoodsClassifyVo2> getTwoClassifyList() {
        return twoClassifyList;
    }

    public void setTwoClassifyList(List<GoodsClassifyVo2> twoClassifyList) {
        this.twoClassifyList = twoClassifyList;
    }
}
