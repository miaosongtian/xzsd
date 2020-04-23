package com.xzsd.pc.topOfColumn.dao;

import com.xzsd.pc.topOfColumn.entity.TopInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TopDao {
    /**
     * 查询顶部栏
     */
    TopInfo getTopOfColumn(@Param("userCode") String userCode);
}
