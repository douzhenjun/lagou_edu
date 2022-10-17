package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;

public interface PromotionAdService {
    
    /*分页查询广告信息, 传递PromotionAdVO对象*/
    PageInfo findAllPromotionAdByPage(PromotionAdVO promotionAdVO);
    
    /*新增广告*/
    void savePromotionAd(PromotionAd promotionAd);
    
    /*回显广告*/
    PromotionAd findPromotionAdById(int id);
    
    /*修改广告*/
    void updatePromotionAd(PromotionAd promotionAd);
    
    /*修改广告的状态*/
    void updatePromotionAdStatus(PromotionAd promotionAd);
}
