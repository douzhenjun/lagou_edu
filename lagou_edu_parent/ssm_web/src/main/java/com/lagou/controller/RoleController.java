package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("role")
public class RoleController {
    
    @Autowired
    private MenuService menuService;
    
    @Autowired
    private RoleService roleService;
    
    /*条件查询*/
    @RequestMapping("findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> roles = roleService.findAllRole(role);
        ResponseResult responseResult = new ResponseResult(roles);
        return responseResult;
    }
    
    /*新增或修改角色*/
    @RequestMapping("saveOrUpdateRole")
    public ResponseResult saveOrUpateRole(@RequestBody Role role){
        if(role.getId() == null){
            roleService.saveRole(role);
        }else{
            roleService.updateRole(role);
        }
        ResponseResult responseResult = new ResponseResult(null);
        return responseResult;
    }
    
    /*获得菜单及其子菜单*/
    @RequestMapping("findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> menuList = menuService.findAllMenu(-1);
        Map content = new HashMap<>();
        content.put("parentMenuList", menuList);
        ResponseResult responseResult = new ResponseResult(content);
        return responseResult;
    }
    
    /*根据角色id查询菜单id*/
    @RequestMapping("findMenuByRoleId")
    public ResponseResult findAllMenuByRoleId(int role_id){
        List<Menu>  menuList = roleService.findAllMenuByRoleId(role_id);
        List<Integer> content = new ArrayList<>();
        for (Menu menu : menuList) {
            content.add(menu.getId());
        }
        ResponseResult responseResult = new ResponseResult(content);
        return responseResult;
    }
    
    /*为角色分配可操作的菜单*/
    @RequestMapping("roleContextMenu")
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.roleContextMenu(roleMenuVo);
        ResponseResult responseResult = new ResponseResult(null);
        return responseResult;
    }
    
    /*删除角色*/
    @RequestMapping("deleteRole")
    public ResponseResult deleteRole(@RequestParam("id") int roleId){
        roleService.deleteRole(roleId);
        ResponseResult responseResult = new ResponseResult(null);
        return responseResult;
    }
}
