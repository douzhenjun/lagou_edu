package com.lagou.dao;

import com.lagou.domain.PromotionSpace;

import java.util.List;

/**
 * 广告位持久层接口
 */
public interface PromotionSpaceMapper {
    /*查询所有的广告位*/
    List<PromotionSpace> findAllPromotionSpace();
    
    /*添加广告位*/
    void savePromotionSpace(PromotionSpace promotionSpace);
    
    /*根据id查询对应广告位信息*/
    PromotionSpace findById(int id);
    
    /*修改广告位*/
    void updatePromotionSpace(PromotionSpace promotionSpace);
}
