package com.xzsd.pc.slideshowHome.entity;

/**
 * 修改轮播图状态参数
 * author:miaosongtian
 * time:2020-4-15
 */
public class SlideshowHome {
    /**
     * 轮播图id
     */
    private String slideshowId;
    /**
     * 轮播图状态
     */
    private String slideshowStateId;
    /**
     * 修改人
     */
    private String userCode;
    /**
     * 版本号
     */
    private String version;

    public String getSlideshowId() {
        return slideshowId;
    }

    public void setSlideshowId(String slideshowId) {
        this.slideshowId = slideshowId;
    }

    public String getSlideshowStateId() {
        return slideshowStateId;
    }

    public void setSlideshowStateId(String slideshowStateId) {
        this.slideshowStateId = slideshowStateId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
