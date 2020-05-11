package com.xzsd.app.clientHome.dao;

import com.xzsd.app.clientHome.entity.ClientHomeInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientHomeDao {
    /**
     * 查询首页轮播图
     */
    List<ClientHomeInfo> listRotationCharHome();
    /**
     * 查询热门商品
     */
    List<ClientHomeInfo> listHotGoods();
}
