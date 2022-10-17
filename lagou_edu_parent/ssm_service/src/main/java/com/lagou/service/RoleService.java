package com.lagou.service;

import com.lagou.domain.Menu;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {
    
    /*根据名字查询角色或者列出所有角色*/
    List<Role> findAllRole(Role role);
    
    /*新增角色*/
    void saveRole(Role role);
    
    /*修改角色*/
    void updateRole(Role role);
    
    /*根据角色id查询菜单id*/
    List<Menu> findAllMenuByRoleId(int role_id);
    
    /*为角色分配菜单信息,这会清除掉原来为该角色分配的菜单信息,再重新添加记录*/
    void roleContextMenu(RoleMenuVo roleMenuVo);
    
    /*删除角色*/
    void deleteRole(int roleId);
    
}
