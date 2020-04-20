package com.xzsd.pc.client.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;

import com.xzsd.pc.client.dao.ClientDao;
import com.xzsd.pc.client.entity.ClientInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClientService {
    @Resource
    private ClientDao clientDao;
    /**
     *分页查询客户信息列表
     * author:miaosongtian
     * time:2020-4-10
     */
    public AppResponse listClients(ClientInfo clientInfo) {
        //当角色为0超级管理员或1管理员时，查询所有客户信息
        if (clientInfo.getRole().equals("1") || clientInfo.getRole().equals("0")) {
            PageHelper.startPage(clientInfo.getPageNum(), clientInfo.getPageSize());
            List<ClientInfo> list = clientDao.listClients(clientInfo);
            // 包装Page对象
            PageInfo<ClientInfo> pageData = new PageInfo<ClientInfo>(list);
            return AppResponse.success("查询成功！", pageData);
        }
        //当角色为2店长时，查询该店长的客户信息
        else if (clientInfo.getRole().equals("2")){
            PageHelper.startPage(clientInfo.getPageNum(), clientInfo.getPageSize());
            List<ClientInfo> list = clientDao.listClientRole2(clientInfo);
            // 包装Page对象
            PageInfo<ClientInfo> pageData = new PageInfo<ClientInfo>(list);
            return AppResponse.success("查询成功！", pageData);
        }
        else return AppResponse.success("该角色没有权限访问！");
    }
}
