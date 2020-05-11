package com.xzsd.pc.goodsClassify.entity;

import java.util.Date;

public class GoodsClassifyVo2 {
    /**
     * 商品分类名称
     */
    private String classifyName;
    /**
     * 商品分类id
     */
    private String classifyId;
    /**
     * 父级编号
     */
    private String classifyParent;
    /**
     * 版本号
     */
    private String version;

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

    public String getClassifyParent() {
        return classifyParent;
    }

    public void setClassifyParent(String classifyParent) {
        this.classifyParent = classifyParent;
    }
}
