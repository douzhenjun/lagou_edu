package com.lagou.dao;

import com.lagou.domain.ResourceCategory;

import java.util.List;

/*资源分类表模块*/
public interface ResourceCategoryMapper {
    
    /*查询所有资源分类*/
    List<ResourceCategory> findAllResourceCategory();
    
    /*新增资源分类*/
    void saveResourceCategory(ResourceCategory resourceCategory);
    
    /*修改资源分类*/
    void updateResourceCategory(ResourceCategory resourceCategory);

    /*删除资源分类*/
    void deleteResourceCategory(int id);
    
    /*根据角色id获得资源分类及其关联的资源信息*/
    List<ResourceCategory> findAllResourceCategoryByRoleId(int rid);
    
    
    
}
