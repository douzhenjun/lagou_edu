package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.CourseVO;

import java.util.List;

public interface CourseContentService {
    
    /*根据课程id查询章节和课时信息*/
    List<CourseSection> findSectionAndLessonByCourseId(int courseId);
    
    /*根据章节的courseId查询课程信息*/
    Course findCourseByCourseId(int courseId);
    
    /*新增章节*/
    void saveSection(CourseSection courseSection);
    
    /*修改章节*/
    void updateSection(CourseSection courseSection);
    
    /*修改章节状态*/
    void updateSectionStatus(CourseSection courseSection);
}
