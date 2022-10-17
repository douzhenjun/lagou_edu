package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.service.PromotionAdService;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {
    
    @Autowired
    private PromotionAdMapper promotionAdMapper;
    
    /*分页查询广告表,通过pageHelper插件调用startPage方法,再由PageInfo对查询结果进行分页展示*/
    @Override
    public PageInfo findAllPromotionAdByPage(PromotionAdVO promotionAdVO) {
        PageHelper.startPage(promotionAdVO.getCurrentPage(), promotionAdVO.getPageSize());
        List<PromotionAd> promotionAdList = promotionAdMapper.findAllAdByPage();
        PageInfo<PromotionAd> promotionAdPageInfo = new PageInfo<>(promotionAdList);
        return promotionAdPageInfo;
    }

    @Override
    public void savePromotionAd(PromotionAd promotionAd) {
        Date date = new Date();
        promotionAd.setCreateTime(date);
        promotionAd.setUpdateTime(date);
        promotionAdMapper.savePromotionAd(promotionAd);
    }

    @Override
    public PromotionAd findPromotionAdById(int id) {
        return promotionAdMapper.findPromotionAdById(id);
    }

    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {
        Date date = new Date();
        promotionAd.setUpdateTime(date);
        promotionAdMapper.updatePromotionAd(promotionAd);
    }

    @Override
    public void updatePromotionAdStatus(PromotionAd promotionAd) {
        Date date = new Date();
        promotionAd.setUpdateTime(date);
        promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }
}
