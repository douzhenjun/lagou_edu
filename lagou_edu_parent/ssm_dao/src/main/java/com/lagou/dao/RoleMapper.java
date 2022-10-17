package com.lagou.dao;

import com.lagou.domain.Menu;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuRelation;
import com.lagou.domain.RoleMenuVo;

import java.util.List;

public interface RoleMapper {
    
    /*新增角色*/
    void saveRole(Role role);
    
    /*查找角色*/
    void updateRole(Role role);
    
    /*查询角色*/
    List<Role> findAllRole(Role role);
    
    /*根据角色id查询关联的菜单id(多对多)*/
    List<Menu> findAllMenuByRoleId(int role_id);
    
    /*删除角色及其关联的菜单信息*/
    void deleteRoleContextMenu(RoleMenuVo roleMenuVo);
    
    /*向角色菜单中间表新增记录*/
    void saveRoleMenuRelation(RoleMenuRelation roleMenuRelation);
    
    /*删除角色*/
    void deleteRole(int roleId);
}
