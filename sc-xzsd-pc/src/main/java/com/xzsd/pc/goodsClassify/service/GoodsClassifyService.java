package com.xzsd.pc.goodsClassify.service;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;

import com.xzsd.pc.goodsClassify.dao.GoodsClassifyDao;
import com.xzsd.pc.goodsClassify.entity.GoodsClassifyInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class GoodsClassifyService {
    @Resource
    private GoodsClassifyDao goodsClassifyDao;
    /**
     * 新增商品分类
     * auother:miaosongtian
     * time:2020-4-9
     */

    @Transactional(rollbackFor = Exception.class)
    public AppResponse addGoodsClassify(GoodsClassifyInfo goodsClassifyInfo){
        goodsClassifyInfo.setClassifyId(StringUtil.getCommonCode(2));
        // 新增商品
        int count = goodsClassifyDao.addGoodsClassify(goodsClassifyInfo);
        if(0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 删除商品分类
     * author:miaosongtian
     * time:2020-04-9
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteGoodsClassify(String classifyId,String userId) {
//        List<String> listId = Arrays.asList(goodsId.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        int count = goodsClassifyDao.deleteGoodsClassify(classifyId,userId);
        if(0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 修改商品分类
     * author:miaosongtian
     * time:2020-04-9
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoodsClassify(GoodsClassifyInfo goodsClassifyInfo) {
        AppResponse appResponse =  AppResponse.success("修改成功！");
        // 修改商品分类
        int count = goodsClassifyDao.updateGoodsClassify(goodsClassifyInfo);
        if(0 == count) {
            appResponse = AppResponse.bizError("修改失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 查询商品分类详情
     * author:miaosongtian
     * time:2020-04-9
     */
    public AppResponse getGoodsClassify(String classifyId){
        GoodsClassifyInfo goodsClassifyInfo = goodsClassifyDao.getGoodsClassify(classifyId);
        return AppResponse.success("查询成功！",goodsClassifyInfo);
    }

    /**
     * 查询商品分类列表
     * author:miaosongtian
     * time:2020-04-9
     */
    public AppResponse listAllGoodsClassify() {
        List<GoodsClassifyInfo> oneClassifyList = goodsClassifyDao.listAllGoodsClassify();
        return AppResponse.success("查询成功！",oneClassifyList);
    }


}
