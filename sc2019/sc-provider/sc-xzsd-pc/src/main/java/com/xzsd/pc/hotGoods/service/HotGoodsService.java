package com.xzsd.pc.hotGoods.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.hotGoods.dao.HotGoodsDao;
import com.xzsd.pc.hotGoods.entity.HotGoodsInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class HotGoodsService {
    @Resource
    private HotGoodsDao hotGoodsDao;

    /**
     * 新增热门商品
     * author:miaosongtian
     * time:2020-4-16
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addHotGoods(HotGoodsInfo hotGoodsInfo){
        // 校验商品编号和序号是否存在
        int countGoodsId = hotGoodsDao.countGoodsId(hotGoodsInfo);
        if(0 != countGoodsId) {
            return AppResponse.bizError("商品编号已存在，请重新输入！");
        }
        int countSort = hotGoodsDao.countSort(hotGoodsInfo);
        if(0 != countSort) {
            return AppResponse.bizError("热门商品序号已存在，请重新输入！");
        }
        hotGoodsInfo.setHotGoodsId(StringUtil.getCommonCode(2));
        // 新增商品
        int count = hotGoodsDao.addHotGoods(hotGoodsInfo);
        if(0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 删除热门商品
     * author:miaosongtian
     * time:2020-04-16
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteHotGoods(String hotGoodsId,String userCode) {
        List<String> listId = Arrays.asList(hotGoodsId.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        int count = hotGoodsDao.deleteHotGoods(listId,userCode);
        if(0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 修改热门商品
     * author:miaosongtian
     * time:2020-4-16
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateHotGoods(HotGoodsInfo hotGoodsInfo) {
        AppResponse appResponse =  AppResponse.success("修改成功！");
        // 校验商品编号和序号是否存在
        int countGoodsId = hotGoodsDao.countGoodsId(hotGoodsInfo);
        if(0 != countGoodsId ) {
            return AppResponse.bizError("商品编号已存在，请重新输入！");
        }
        int countSort = hotGoodsDao.countSort(hotGoodsInfo);
        if(0 != countSort) {
            return AppResponse.bizError("热门商品序号已存在，请重新输入！");
        }
        // 修改热门商品
        int count = hotGoodsDao.updateHotGoods(hotGoodsInfo);
        if(0 == count) {
            appResponse = AppResponse.bizError("修改失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 查询热门商品详情
     * author:miaosongtian
     * time:2020-03-29
     */
    public AppResponse getHotGoods(String hotGoodsId){
        HotGoodsInfo hotGoodsInfo = hotGoodsDao.getHotGoods(hotGoodsId);
        return AppResponse.success("查询成功！",hotGoodsInfo);
    }

    /**
     * 分页查询热门商品列表
     * author:miaosongtian
     * time:2020-04-16
     */
    public AppResponse listHotGoods(HotGoodsInfo hotGoodsInfo) {
        PageHelper.startPage(hotGoodsInfo.getPageNum(), hotGoodsInfo.getPageSize());
        List<HotGoodsInfo> hotGoodsInfoList = hotGoodsDao.listHotGoods(hotGoodsInfo);
        // 包装Page对象
        PageInfo<HotGoodsInfo> pageData = new PageInfo<HotGoodsInfo>(hotGoodsInfoList);
        return AppResponse.success("查询成功！", pageData);
    }

    /**
     * 查询热门商品展示数量
     * author:miaosongtian
     * time:2020-4-17
     */
    public AppResponse getHotGoodsShowNum(){
        HotGoodsInfo hotGoodsInfo = hotGoodsDao.getHotGoodsShowNum();
        return AppResponse.success("查询成功！",hotGoodsInfo);
    }

    /**
     * 修改热门商品展示数量
     * author:miaosongtian
     * time:2020-4-17
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateHotGoodsShowNum(HotGoodsInfo hotGoodsInfo) {
        AppResponse appResponse =  AppResponse.success("修改成功！");
        int count = hotGoodsDao.updateHotGoodsShowNum(hotGoodsInfo);
        if(0 == count) {
            appResponse = AppResponse.bizError("修改失败，请重试！");
        }
        return appResponse;
    }
}
