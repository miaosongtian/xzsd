package com.xzsd.pc.store.dao;

import com.xzsd.pc.store.entity.StoreInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreDao {
    /**
     * 统计门店营业执照编号数量
     */
    int countBusinessCode(StoreInfo storeInfo);
    /**
     * 统计店长编号数量
     */
    int countUserId(StoreInfo storeInfo);
    /**
     * 新增门店
     */
    int addStore(StoreInfo storeInfo);
    /**
     * 分页查询门店列表（管理员）
     */
    List<StoreInfo> listStores(StoreInfo storeInfo);
    /**
     * 查询门店列表（店长）
     */
    StoreInfo listStoresRole2(StoreInfo storeInfo);
    /**
     * 查询门店详情
     */
    StoreInfo getStore(@Param("storeId") String storeId);
    /**
     * 修改门店
     */
    int updateStore(StoreInfo storeInfo);
    /**
     * 删除门店
     */
    int deleteStore(@Param("listId") List<String> listId,@Param("userCode") String userCode);
}
