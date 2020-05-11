package com.xzsd.app.managerInformation.entity;

public class ManagerInformationInfo {
    /**
     * 一页几条
     */
    private int pageSize;
    /**
     * 第几页
     */
    private int pageNum;
    /**
     * 司机姓名
     */
    private String userName;
    /**
     * 司机电话号码
     */
    private String phone;
    /**
     * 店长编号
     */
    private String userId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
