package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {
    
    @Autowired
    private PromotionAdService promotionAdService;
    
    /*分页查询广告信息列表, 默认查找第一页,每页5条*/
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVO promotionAdVO){
        PageInfo pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVO);
        ResponseResult responseResult = new ResponseResult(pageInfo);
        return responseResult;
    }
    
    /*图片上传功能, 上传地址为根路径下的upload*/
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) throws IOException {
        //1. 判断携带的图片是否为空
        if(multipartFile.isEmpty()){
            throw new RuntimeException();
        }

        //2. 获得图片原始名称, 将它变成唯一的名称
        String origin_filename = multipartFile.getOriginalFilename();
        String filename = System.currentTimeMillis() + origin_filename.substring(origin_filename.lastIndexOf('.'));

        //3. 获得webappp上下文路径(文件输出路径)
        //D:\software\java\java_work\lagou_edu_management_system\my\lagou_edu_parent\ssm_web\src\main\webapp\
        String origin_upload = request.getServletContext().getRealPath("/");
//        System.out.println(origin_upload);

        //4. 新建一个文件夹, 用来接收上传来的图片
        String upload = origin_upload.substring(0, origin_upload.indexOf("ssm_web")) + "/upload/" + filename;
        File filepath = new File(upload);
        // 如果没有创建upload这个目录, 就创建
        if(!filepath.getParentFile().exists()){
            filepath.getParentFile().mkdirs();
        }
        //5. 上传文件到指定路径
        multipartFile.transferTo(filepath);

        //6. 用map封装fileName和filePath这两个字符串
        Map<String, String> content = new HashMap<>();
        content.put("fileName", filename);
        content.put("filePath", upload);

        //7. 返回指定格式的json串, 传入map
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", content);
        return responseResult;
    }
    
    /*新增或修改广告信息*/
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){
        if(promotionAd.getId() == null){
            promotionAdService.savePromotionAd(promotionAd);
        }else{
            promotionAdService.updatePromotionAd(promotionAd);
        }
        ResponseResult responseResult = new ResponseResult(null);
        return responseResult;
    }
    
    /*回显广告信息*/
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(int id){
        PromotionAd content =  promotionAdService.findPromotionAdById(id);
        ResponseResult responseResult = new ResponseResult(content);
        return responseResult;
    }
    
    /*切换广告状态上下线*/
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(PromotionAd promotionAd){
        promotionAdService.updatePromotionAdStatus(promotionAd);
        Map<String, Integer> content = new HashMap<>();
        content.put("status", promotionAd.getStatus());
        ResponseResult responseResult = new ResponseResult(content);
        return responseResult;
    }
    
}
