package com.xzsd.pc.slideshowHome.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.goods.entity.GoodsInfo;
import com.xzsd.pc.slideshowHome.dao.SlideshowHomeDao;
import com.xzsd.pc.slideshowHome.entity.SlideshowHome;
import com.xzsd.pc.slideshowHome.entity.SlideshowHomeInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SlideshowHomeService {
    @Resource
    private SlideshowHomeDao slideshowHomeDao;
    /**
     * 新增轮播图
     * author:miaosongtian
     * time:2020-4-13
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addSlideshowHome(SlideshowHomeInfo slideshowHomeInfo){
        // 校验商品编号和序号是否存在
        int countGoodsId = slideshowHomeDao.countGoodsId(slideshowHomeInfo);
        if(0 != countGoodsId) {
            return AppResponse.bizError("商品编号已存在，请重新输入！");
        }
        int countSort = slideshowHomeDao.countSort(slideshowHomeInfo);
        if(0 != countSort) {
            return AppResponse.bizError("轮播图序号已存在，请重新输入！");
        }
        slideshowHomeInfo.setSlideshowId(StringUtil.getCommonCode(2));
        // 新增商品
        int count = slideshowHomeDao.addSlideshowHome(slideshowHomeInfo);
        if(0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 删除轮播图
     * author:miaosongtian
     * time:2020-4-13
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteSlideshowHome(String slideshowId,String userCode) {
        List<String> listId = Arrays.asList(slideshowId.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        int count = slideshowHomeDao.deleteSlideshowHome(listId,userCode);
        if(0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 分页查询轮播图列表
     * author:miaosongtian
     * time:2020-04-13
     */
    public AppResponse listSlideshowHome(SlideshowHomeInfo slideshowHomeInfo) {
        PageHelper.startPage(slideshowHomeInfo.getPageNum(), slideshowHomeInfo.getPageSize());
        List<SlideshowHomeInfo> goodsInfoList = slideshowHomeDao.listSlideshowHome(slideshowHomeInfo);
        // 包装Page对象
        PageInfo<SlideshowHomeInfo> pageData = new PageInfo<SlideshowHomeInfo>(goodsInfoList);
        return AppResponse.success("查询成功！", pageData);
    }

    /**
     * 分页查询商品列表
     * author:miaosongtian
     * time:2020-04-13
     */
    public AppResponse listGoods(GoodsInfo goodsInfo) {
        PageHelper.startPage(goodsInfo.getPageNum(), goodsInfo.getPageSize());
        List<GoodsInfo> goodsInfoList = slideshowHomeDao.listGoods(goodsInfo);
        // 包装Page对象
        PageInfo<GoodsInfo> pageData = new PageInfo<GoodsInfo>(goodsInfoList);
        return AppResponse.success("查询成功！", pageData);
    }

    /**
     * 修改轮播图状态
     * author:miaosongtian
     * time:2020-04-15
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateSlideshowHomeState(SlideshowHomeInfo slideshowHomeInfo) {
        //把字符串通过逗号分成list
        List<String> listsId = Arrays.asList(slideshowHomeInfo.getSlideshowId().split(","));
        List<String> listVersion = Arrays.asList(slideshowHomeInfo.getVersion().split(","));
        //定义一个变量用于存放listId,listVersion
        List<SlideshowHome> listUpdata = new ArrayList<>();
        String slideshowStateId = slideshowHomeInfo.getSlideshowStateId();
        String userCode = slideshowHomeInfo.getLastModifiedBy();
        for (int i = 0;i < listsId.size();i++){
            SlideshowHome slideshowHome = new SlideshowHome();
            slideshowHome.setSlideshowStateId(slideshowStateId);
            slideshowHome.setUserCode(userCode);
            slideshowHome.setVersion(listVersion.get(i));
            slideshowHome.setSlideshowId(listsId.get(i));
            listUpdata.add(slideshowHome);
        }
        AppResponse appResponse = AppResponse.success("状态修改成功！");
        int count = slideshowHomeDao.updateSlideshowHomeState(listUpdata);
        if(0 == count) {
            appResponse = AppResponse.bizError("状态修改失败，请重试！");
        }
        return appResponse;
    }

}
