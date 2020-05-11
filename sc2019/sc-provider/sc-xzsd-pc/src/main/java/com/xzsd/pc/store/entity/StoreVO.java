package com.xzsd.pc.store.entity;

import java.util.List;

/**
 * 店长查询门店信息（封装）
 * 用于返回
 */
public class StoreVO {
    /**
     * 门店信息集合
     */
   private List<StoreInfo> list;
    /**
     * 总数
     */
    private String total;
    /**
     * 页数
     */
    private String pageNum;
    /**
     * 显示数量
     */
    private String pageSize;

    public List<StoreInfo> getList() {
        return list;
    }

    public void setList(List<StoreInfo> list) {
        this.list = list;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
