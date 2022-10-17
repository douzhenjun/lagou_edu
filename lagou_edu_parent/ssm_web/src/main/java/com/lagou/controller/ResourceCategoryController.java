package com.lagou.controller;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("resourceCategory")
public class ResourceCategoryController {
    @Autowired
    private ResourceCategoryService resourceCategoryService;
    
    /*查询资源分类列表*/
    @RequestMapping("findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){
        List<ResourceCategory> resourceCategoryList = resourceCategoryService.findAllResourceCategory();
        ResponseResult responseResult = new ResponseResult(resourceCategoryList);
        return responseResult;
    }
    
    /*新增或修改资源分类*/
    @RequestMapping("saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory){
        if(resourceCategory.getId() == null){
            resourceCategoryService.saveResourceCategory(resourceCategory);
        }else{
            resourceCategoryService.updateResourceCategory(resourceCategory);
        }
        return new ResponseResult(null);
    }
    
    /*删除资源分类*/
    @RequestMapping("deleteResourceCategory")
    public ResponseResult deleteResourceCategory(int id){
        resourceCategoryService.deleteResourceCategory(id);
        return new ResponseResult(null);
    }
    
}
