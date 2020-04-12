package com.xzsd.pc.client.dao;

import com.xzsd.pc.client.entity.ClientInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientDao {
    /**
     * 分页查询客户信息列表
     */
    List<ClientInfo> listClients(ClientInfo clientInfo);
}
