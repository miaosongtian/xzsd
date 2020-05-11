package com.xzsd.pc.menu.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.menu.entity.MenuInfo;
import com.xzsd.pc.menu.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("menu")
public class MenuController {
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
    @Resource
    private MenuService menuService;

    /**
     *  新增菜单
     * author:miaosongtian
     * time:2020-04-20
     */
    @PostMapping("addMenu")
    public AppResponse addMenu(MenuInfo menuInfo) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            menuInfo.setCreateBy(userId);
            AppResponse appResponse = menuService.addMenu(menuInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("菜单新增异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除菜单
     * author:miaosongtian
     * time:2020-04-20
     */
    @PostMapping("deleteMenu")
    public AppResponse deleteMenu (String menuId){
        try{
            //删除菜单
            String userId =  SecurityUtils.getCurrentUserId();
            MenuInfo menuInfo = new MenuInfo();
            menuInfo.setLastModifiedBy(userId);
            return menuService.deleteMenu(menuId,userId);
        }catch (Exception e) {
            logger.error("菜单删除错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改菜单
     * author:miaosongtian
     * time:2020-04-20
     */
    @PostMapping("updateMenu")
    public AppResponse updateMenu (MenuInfo menuInfo){
        try{
            //修改商品分类
            String userId =  SecurityUtils.getCurrentUserId();
            menuInfo.setLastModifiedBy(userId);
            return menuService.updateMenu(menuInfo);
        }catch (Exception e) {
            logger.error("菜单修改异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询菜单列表（查全部）
     * author:miaosongtian
     * time:2020-04-20
     */
    @PostMapping("listMenu")
    public AppResponse listMenu(){
        try {
            return menuService.listMenu();
        } catch (Exception e) {
            logger.error("查询菜单列表异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询菜单列表（根据角色）
     * author:miaosongtian
     * time:2020-04-20
     */
    @PostMapping("listMenuHome")
    public AppResponse listMenuHome(String role){
        try {
            return menuService.listMenuHome(role);
        } catch (Exception e) {
            logger.error("查询菜单列表异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询菜单详情
     * author:miaosongtian
     * time:2020-04-20
     */
    @PostMapping("getMenu")
    public AppResponse getMenu (String menuId){
        try{
            //查询菜单详情
            return menuService.getMenu(menuId);
        }catch (Exception e) {
            logger.error("菜单查询详情错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
