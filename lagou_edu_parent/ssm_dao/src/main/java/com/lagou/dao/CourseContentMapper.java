package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {
    
    /*根据课程名查询,返回对应的章节信息和课时信息*/
    List<CourseSection> findSectionAndLessonByCourseId(int courseId);
    
    /*回显章节对应的课程信息*/
    Course findCourseByCourseId(int courseId);
    
    /*新增章节信息*/
    void saveSection(CourseSection courseSection);
    
    /*修改章节信息*/
    void updateSection(CourseSection courseSection);
    
    /*修改章节状态*/
    void updateSectionStatus(CourseSection courseSection);
}
