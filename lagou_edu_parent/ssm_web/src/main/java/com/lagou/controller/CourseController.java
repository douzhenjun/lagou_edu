package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
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
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    
    /*根据条件查询课程*/
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){
        List<Course> courseList = courseService.findCourseByCondition(courseVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", courseList);
        return responseResult;
    }
    
    @RequestMapping("/courseUpload")
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
        String upload = origin_upload.substring(0, origin_upload.indexOf("webapp")) + "/upload/" + filename;
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
    
    /*添加或修改课程信息*/
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO){
        
        //根据是否传递id来判断是添加还是更新,如果没有是添加,如果有是修改
        if(courseVO.getId() == null){
            courseService.saveCourseOrTeacher(courseVO);
            
        } else{
            courseService.updateCourseOrTeacher(courseVO);
        }
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", null);
        return responseResult;
    }
    
    
    /*回显课程信息*/
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(int id){
        CourseVO content = courseService.findCourseById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", content);
        return responseResult;
    }
    
    /*切换课程上下架状态*/
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(@RequestParam("id") int id, @RequestParam("status") int status){
        courseService.updateCourseStatus(id, status);
        Map content = new HashMap();
        content.put("status", status);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", content);
        return responseResult;
    }
    
}
