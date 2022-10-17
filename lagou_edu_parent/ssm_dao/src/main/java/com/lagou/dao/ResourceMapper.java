package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;
import com.lagou.domain.RoleResourceRelation;
import com.lagou.domain.RoleResourceVo;

import java.util.List;

/*资源模块*/
public interface ResourceMapper {
    
    /*分页条件查询资源*/
    List<Resource> findAllResourceByPage(ResourceVo resourceVo);
    
    /*清空角色和资源的关系中间表*/
    void deleteRoleResource(RoleResourceVo roleResourceVo);
    
    /*新增角色资源关系表*/
    void saveRoleResource(RoleResourceRelation roleResourceRelation);
}
