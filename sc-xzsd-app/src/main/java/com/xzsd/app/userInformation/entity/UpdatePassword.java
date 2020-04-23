package com.xzsd.app.userInformation.entity;

/**
 * 个人信息界面修改密码参数
 */
public class UpdatePassword {
    /**
     * 原密码
     */
    private String userPassword;
    /**
     * 新密码
     */
    private String userNewPassword;
    /**
     * 操作用户
     */
    private String userCode;
    /**
     * 版本号
     */
    private String version;

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserNewPassword() {
        return userNewPassword;
    }

    public void setUserNewPassword(String userNewPassword) {
        this.userNewPassword = userNewPassword;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
