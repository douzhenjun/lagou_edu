package com.lagou.service;

import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService {
    /*查询所有广告位列表*/
    List<PromotionSpace> findAllPromotionSpace();
    
    /*新增广告位*/
    void savePromotionSpace(PromotionSpace promotionSpace);
    
    /*回显广告位信息,根据id查询广告位*/
    PromotionSpace findPromotionSpaceById(int id);
    
    /*修改广告位信息*/
    void updatePromotionSpace(PromotionSpace promotionSpace);
}
