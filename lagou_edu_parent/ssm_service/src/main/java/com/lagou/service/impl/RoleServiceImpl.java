package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Menu;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuRelation;
import com.lagou.domain.RoleMenuVo;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> findAllRole(Role role) {
        return roleMapper.findAllRole(role);
    }

    @Override
    public void saveRole(Role role) {
        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        roleMapper.saveRole(role);
    }

    @Override
    public void updateRole(Role role) {
        Date date = new Date();
        role.setUpdatedTime(date);
        roleMapper.updateRole(role);
    }

    @Override
    public List<Menu> findAllMenuByRoleId(int role_id) {
        return roleMapper.findAllMenuByRoleId(role_id);
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {
        //1.首先删除掉该角色与菜单表的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVo);
        //2.重新根据选中的菜单信息,封装到roleMenuVo对象,根据对象里的roleId和menuIdList逐一插入数据
        for (Integer menuId : roleMenuVo.getMenuIdList()) {
            //3.新建roleMenuRelation中间表对象,用于携带参数,传递到持久层
            RoleMenuRelation roleMenuRelation = new RoleMenuRelation();
            roleMenuRelation.setMenuId(menuId);
            roleMenuRelation.setRoleId(roleMenuVo.getRoleId());
            Date date = new Date();
            roleMenuRelation.setCreatedTime(date);
            roleMenuRelation.setUpdatedTime(date);
            roleMenuRelation.setCreatedBy("system");
            roleMenuRelation.setUpdatedBy("system");
            //4.将封装好数据的中间表对象传递到持久层,调用save方法
            roleMapper.saveRoleMenuRelation(roleMenuRelation);
        }
    }

    @Override
    public void deleteRole(int roleId) {
        RoleMenuVo roleMenuVo = new RoleMenuVo();
        roleMenuVo.setRoleId(roleId);
        //首先删除角色与菜单的关联关系中间表
        roleMapper.deleteRoleContextMenu(roleMenuVo);
        //然后删除角色表中的角色信息
        roleMapper.deleteRole(roleId);
    }
}
