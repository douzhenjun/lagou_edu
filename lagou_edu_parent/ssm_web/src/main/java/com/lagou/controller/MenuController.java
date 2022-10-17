package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("menu")
public class MenuController {
    
    @Autowired
    private MenuService menuService;
    
    @RequestMapping("findAllMenu")
    public ResponseResult findAllMenu(){
        PageInfo<Menu> menuPageInfo = menuService.findMenuList();
        ResponseResult responseResult = new ResponseResult(menuPageInfo);
        return responseResult;
    }
    
    @RequestMapping("saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu){
        if(menu.getId() == null){
            menuService.saveMenu(menu);
        }else{
            menuService.updateMenu(menu);
        }
        return new ResponseResult(null);
    }
    
    /*回显菜单信息, 根据传入的id判断, 若id=-1,说明是新增操作,只回显父菜单信息,
    * 若id不是-1, 说明是修改操作, 既要回显父菜单信息, 也要回显菜单信息*/
    @RequestMapping("findMenuInfoById")
    public ResponseResult findMenuInfoById(int id){
        if(id == -1){
            //执行添加操作,不需要回显菜单信息
            List<Menu> menuList = menuService.findAllMenu(-1);
            //封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo", null);
            map.put("parentMenuList", menuList);
            return new ResponseResult(map);
        }else{
            Menu menu = menuService.findMenuById(id);
            List<Menu> menuList = menuService.findAllMenu(id);
            //封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo", menu);
            map.put("parentMenuList", menuList);
            return new ResponseResult(map);
        }
    }
}
