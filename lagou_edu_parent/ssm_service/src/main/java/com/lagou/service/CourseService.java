package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {
    
    /*根据条件查询课程信息*/
    List<Course> findCourseByCondition(CourseVO courseVO);
    
    /*保存前端页面信息*/
    void saveCourseOrTeacher(CourseVO courseVO);
    
    /*修改课程或讲师信息*/
    void updateCourseOrTeacher(CourseVO courseVO);
    
    /*根据id查询课程信息*/
    CourseVO findCourseById(int id);
    
    /*切换课程上下架状态*/
    void updateCourseStatus(int id, int status);
}
