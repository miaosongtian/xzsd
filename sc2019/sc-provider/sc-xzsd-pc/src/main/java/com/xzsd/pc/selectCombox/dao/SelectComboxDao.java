package com.xzsd.pc.selectCombox.dao;

import com.xzsd.pc.selectCombox.entity.SelectComboxInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface SelectComboxDao {
    /**
     * 查询省市区下拉框
     */
    List<SelectComboxInfo> listArea(@Param("areaId") String areaId);
}
