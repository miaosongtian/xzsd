package com.xzsd.pc.menu.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.menu.dao.MenuDao;
import com.xzsd.pc.menu.entity.MenuInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService {
    @Resource
    private MenuDao menuDao;

    /**
     * 新增菜单
     * auother:miaosongtian
     * time:2020-4-20
     */

    @Transactional(rollbackFor = Exception.class)
    public AppResponse addMenu(MenuInfo menuInfo){
        menuInfo.setMenuId(StringUtil.getCommonCode(2));
        // 新增商品
        int count = menuDao.addMenu(menuInfo);
        if(0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 删除菜单
     * author:miaosongtian
     * time:2020-04-20
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteMenu(String menuId,String userId) {
        AppResponse appResponse = AppResponse.success("删除成功！");
        int count = menuDao.deleteMenu(menuId,userId);
        if(0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 修改菜单
     * author:miaosongtian
     * time:2020-04-20
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateMenu(MenuInfo menuInfo) {
        AppResponse appResponse =  AppResponse.success("修改成功！");
        // 修改商品分类
        int count = menuDao.updateMenu(menuInfo);
        if(0 == count) {
            appResponse = AppResponse.bizError("修改失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 查询菜单列表（查全部）
     * author:miaosongtian
     * time:2020-04-20
     */
    public AppResponse listMenu() {
        List<MenuInfo> menuList = menuDao.listMenu();
        return AppResponse.success("查询成功！",menuList);
    }

    /**
     * 查询菜单列表（根据角色查）
     * author:miaosongtian
     * time:2020-04-20
     */
    public AppResponse listMenuHome(String role) {
        //角色为1/0查询全部菜单，角色为2查询店长菜单
        if (role.equals("2")){
            List<MenuInfo> menuList = menuDao.listMenuHome(role);
            return AppResponse.success("查询成功！",menuList);
        }else if (role.equals("1") || role.equals("0")){
            List<MenuInfo> menuList = menuDao.listMenu();
            return AppResponse.success("查询成功！",menuList);
        } else return AppResponse.success("角色没有权限！");

    }

    /**
     * 查询菜单详情
     * author:miaosongtian
     * time:2020-04-20
     */
    public AppResponse getMenu(String menuId){
        MenuInfo menuInfo = menuDao.getMenu(menuId);
        return AppResponse.success("查询成功！",menuInfo);
    }
}
