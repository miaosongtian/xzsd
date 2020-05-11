package com.xzsd.pc.goodsClassify.service;




import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;

import com.xzsd.pc.goodsClassify.dao.GoodsClassifyDao;
import com.xzsd.pc.goodsClassify.entity.GoodsClassifyInfo;
import com.xzsd.pc.goodsClassify.entity.GoodsClassifyVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
    public AppResponse deleteGoodsClassify(GoodsClassifyInfo goodsClassifyInfo) {
        AppResponse appResponse = AppResponse.success("删除成功！");
        if (goodsClassifyInfo.getClassifyParent().equals("0")){
            if (goodsClassifyDao.countTwoClassify(goodsClassifyInfo.getClassifyId()) != 0){
                return AppResponse.bizError("一级分类下存在二级分类，不可删除！");
            }
        }else{
            if (goodsClassifyDao.countGoods(goodsClassifyInfo.getClassifyId()) != 0){
                return AppResponse.bizError("二级分类下存在商品，不可删除！");
            }
        }
        int count = goodsClassifyDao.deleteGoodsClassify(goodsClassifyInfo);
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
        if (goodsClassifyInfo == null){
            return AppResponse.success("详情查询失败，请重试！");
        }
        return AppResponse.success("查询成功！",goodsClassifyInfo);
    }

    /**
     * 查询商品分类列表
     * author:miaosongtian
     * time:2020-04-9
     */
    public AppResponse listAllGoodsClassify() {
        GoodsClassifyVo oneClassifyList = new GoodsClassifyVo();
        oneClassifyList.setOneClassifyList(goodsClassifyDao.listAllGoodsClassify());
        return AppResponse.success("查询成功！",oneClassifyList);
    }
}
