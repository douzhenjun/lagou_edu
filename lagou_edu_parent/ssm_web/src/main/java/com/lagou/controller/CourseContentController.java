package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {
    @Autowired
    private CourseContentService courseContentService;
    
    /*根据课程id查询章节和课时信息*/
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLesson(int courseId){
        List<CourseSection> content = courseContentService.findSectionAndLessonByCourseId(courseId);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", content);
        return responseResult;
    }
    
    /*回显章节对应的课程信息(id,course_name)*/
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(int courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);
        Map<String, Object> content = new HashMap<>();
        content.put("id", course.getId());
        content.put("courseName", course.getCourseName());
        ResponseResult responseResult = new ResponseResult(content);
        return responseResult;
    }
    
    /*新增或修改章节信息*/
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        if(courseSection.getId() == null){
            courseContentService.saveSection(courseSection);
        }else{
            courseContentService.updateSection(courseSection);
        }
        ResponseResult responseResult = new ResponseResult(null);
        return responseResult;
    }
    
    /*修改章节的状态, 0隐藏,1待更新,2已发布*/
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id, int status){
        CourseSection courseSection = new CourseSection();
        courseSection.setId(id);
        courseSection.setStatus(status);
        courseContentService.updateSectionStatus(courseSection);
        Map<String, Integer> content = new HashMap<>();
        content.put("status", status);
        ResponseResult responseResult = new ResponseResult(content);
        return responseResult;
    }
}
