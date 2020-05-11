package com.xzsd.app.clientHome.entity;

import java.util.List;

public class ClientVO {
    /**
     * 首页轮播图信息集合
     */
    private List<ClientHomeInfo> slideshowList;
    /**
     * 热门商品信息集合
     */
    private List<ClientHomeInfo> list;

    public List<ClientHomeInfo> getSlideshowList() {
        return slideshowList;
    }

    public void setSlideshowList(List<ClientHomeInfo> slideshowList) {
        this.slideshowList = slideshowList;
    }

    public List<ClientHomeInfo> getList() {
        return list;
    }

    public void setList(List<ClientHomeInfo> list) {
        this.list = list;
    }
}
