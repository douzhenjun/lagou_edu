package com.lagou.dao;

import com.lagou.domain.ResourceCategory;

import java.util.List;

/*资源分类表模块*/
public interface ResourceCategoryMapper {
    
    /*查询所有资源分类*/
    List<ResourceCategory> findAllResourceCategory();
}
