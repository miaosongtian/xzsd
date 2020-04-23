package com.xzsd.pc.topOfColumn.entity;

/**
 * 顶部栏响应数据
 * author:miaosongtian
 * time:2020-4-21
 */
public class TopInfo {
    /**
     * 当前登录用户姓名
     */
    private String userName;
    /**
     * 当前登录用户头像路径
     */
    private String userImage;
    /**
     * 当前登录用户id
     */
    private String userId;
    /**
     * 当前登录用户角色
     */
    private String role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
