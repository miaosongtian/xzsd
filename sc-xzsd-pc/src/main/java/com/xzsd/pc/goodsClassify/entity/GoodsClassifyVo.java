package com.xzsd.pc.goodsClassify.entity;

import java.util.Date;

public class GoodsClassifyVo {
    /**
     * 商品分类名称
     */
    private String secClassifyName;
    /**
     * 商品分类id
     */
    private String secClassifyId;
    /**
     * 商品分类备注
     */
    private String secClassifyComment;
    /**
     * 父级编号
     */
    private String secClassifyParent;
    /**
     * 备注
     */
    private String remark;
    /**
     * 作废标记 0为存在，1为作废
     */
    private int isDeleted;
    /**
     * 序号
     */
    private int sortNo;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 更新时间
     */
    private Date gmtModified;
    /**
     * 更新者
     */
    private String lastModifiedBy;
    /**
     * 版本号
     */
    private String secVersion;

    public String getSecClassifyName() {
        return secClassifyName;
    }

    public void setSecClassifyName(String secClassifyName) {
        this.secClassifyName = secClassifyName;
    }

    public String getSecClassifyId() {
        return secClassifyId;
    }

    public void setSecClassifyId(String secClassifyId) {
        this.secClassifyId = secClassifyId;
    }

    public String getSecClassifyComment() {
        return secClassifyComment;
    }

    public void setSecClassifyComment(String secClassifyComment) {
        this.secClassifyComment = secClassifyComment;
    }

    public String getSecClassifyParent() {
        return secClassifyParent;
    }

    public void setSecClassifyParent(String secClassifyParent) {
        this.secClassifyParent = secClassifyParent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getSecVersion() {
        return secVersion;
    }

    public void setSecVersion(String secVersion) {
        this.secVersion = secVersion;
    }
}
