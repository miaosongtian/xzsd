package com.xzsd.pc.store.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.store.dao.StoreDao;
import com.xzsd.pc.store.entity.StoreInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.*;

@Service
public class StoreService {
    @Resource
    private StoreDao storeDao;

    /**
     * 新增门店
     * author:miaosongtian
     * time:2020-4-18
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addStore(StoreInfo storeInfo){
        // 校验营业执照是否存在
        int countBusinessCode = storeDao.countBusinessCode(storeInfo);
        if(0 != countBusinessCode) {
            return AppResponse.bizError("营业执照编号已存在，请重新输入！");
        }
        storeInfo.setStoreId(StringUtil.getCommonCode(2));
        //生成随机邀请码
        storeInfo.setInviteCode(getRandomString(6));
        // 新增门店
        int count = storeDao.addStore(storeInfo);
        if(0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 生成随机邀请码
     * author:miaosongtian
     * time:2020-4-18
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 分页查询门店列表（按角色查数据）
     * author:miaosongtian
     * time:2020-04-19
     */
    public AppResponse listStores(StoreInfo storeInfo) {
        //当角色为0超级管理员或1管理员时，查询所有数据
        if (storeInfo.getRole().equals("1") || storeInfo.getRole().equals("0")){
            PageHelper.startPage(storeInfo.getPageNum(), storeInfo.getPageSize());
            List<StoreInfo> storeInfoList = storeDao.listStores(storeInfo);
            // 包装Page对象
            PageInfo<StoreInfo> pageData = new PageInfo<StoreInfo>(storeInfoList);
             return AppResponse.success("查询成功！", pageData);
        }
        //当角色为2店长时，查询该店长的数据
        else if (storeInfo.getRole().equals("2")){
            StoreInfo storeInfoRole2 = storeDao.listStoresRole2(storeInfo);
            return AppResponse.success("查询成功！", storeInfoRole2);
        }
        else return AppResponse.success("角色异常！");
    }

    /**
     * 查询门店详情
     * author:miaosongtian
     * time:2020-4-19
     */
    public AppResponse getStore(String storeId){
        StoreInfo storeInfo = storeDao.getStore(storeId);
        return AppResponse.success("查询成功！",storeInfo);
    }

    /**
     * 修改门店
     * author:miaosongtian
     * time:2020-4-19
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateStore(StoreInfo storeInfo) {
        AppResponse appResponse =  AppResponse.success("修改成功！");
        // 校验营业执照是否存在
        int countBusinessCode = storeDao.countBusinessCode(storeInfo);
        if(0 != countBusinessCode) {
            return AppResponse.bizError("营业执照编号已存在，请重新输入！");
        }
        // 修改门店
        int count = storeDao.updateStore(storeInfo);
        if(0 == count) {
            appResponse = AppResponse.bizError("修改失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 删除门店
     * author:miaosongtian
     * time:2020-4-19
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteStore(String storeId,String userCode) {
        List<String> listId = Arrays.asList(storeId.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        int count = storeDao.deleteStore(listId,userCode);
        if(0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }
}
