package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
public interface CourseMapper {
    /*根据条件查找课程*/
    List<Course> findCourseByCondition(CourseVO courseVO);
    
    /*保存课程信息*/
    int saveCourse(Course course);
    
    /*保存讲师信息*/
    void saveTeacher(Teacher teacher);
    
    /*修改课程信息*/
    void updateCourse(Course course);
    
    /*修改讲师信息*/
    void updateTeacher(Teacher teacher);
    
    /*根据id查询对应的课程信息及关联的讲师信息*/
    CourseVO findCourseById(int id);
    
    /*修改课程的状态(上架/下架)*/
    void updateCourseStatus(Course course);
}
