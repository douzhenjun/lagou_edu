package com.lagou.service;

import com.lagou.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryService {
    
    /*查找所有资源分类*/
    List<ResourceCategory> findAllResourceCategory();
    
    /*新增资源分类*/
    void saveResourceCategory(ResourceCategory resourceCategory);
    
    /*修改资源分类*/
    void updateResourceCategory(ResourceCategory resourceCategory);
    
    /*删除资源分类*/
    void deleteResourceCategory(int id);
    
    /*根据roleid获得资源分类及其对应的资源*/
    List<ResourceCategory> findAllResourceCategoryByRoleId(int id);
}
