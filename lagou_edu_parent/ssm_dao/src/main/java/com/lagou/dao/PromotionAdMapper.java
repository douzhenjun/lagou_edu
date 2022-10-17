package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

/*广告信息模块*/
public interface PromotionAdMapper {
    
    /*广告信息的分页查询*/
    List<PromotionAd> findAllAdByPage();
    
    /*新增广告*/
    void savePromotionAd(PromotionAd promotionAd);
    
    /*回显广告*/
    PromotionAd findPromotionAdById(int id);
    
    /*修改广告*/
    void updatePromotionAd(PromotionAd promotionAd);
    
    /*广告状态上下线*/
    void updatePromotionAdStatus(PromotionAd promotionAd);
}
