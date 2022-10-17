package com.lagou.controller;


import com.lagou.domain.PromotionSpace;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("PromotionSpace")
public class PromotionSpaceController {
    
    @Autowired
    private PromotionSpaceService promotionSpaceService;
    
    /*广告位列表查询*/
    @RequestMapping("findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){
        List<PromotionSpace> content = promotionSpaceService.findAllPromotionSpace();
        ResponseResult responseResult = new ResponseResult(content);
        return responseResult;
    }
    
    /*新增或修改广告位*/
    @RequestMapping("saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace){
        if(promotionSpace.getId() == null)
            promotionSpaceService.savePromotionSpace(promotionSpace);
        else
            promotionSpaceService.updatePromotionSpace(promotionSpace);
        ResponseResult responseResult = new ResponseResult(null);
        return responseResult;
    }
    
    
    /*回显广告位*/
    @RequestMapping("findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(int id){
       PromotionSpace content = promotionSpaceService.findPromotionSpaceById(id);
        ResponseResult responseResult = new ResponseResult(content);
        return responseResult;
    }
    
}
