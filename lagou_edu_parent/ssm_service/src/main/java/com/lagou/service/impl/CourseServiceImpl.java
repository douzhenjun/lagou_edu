package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public List<Course> findCourseByCondition(CourseVO courseVO) {
        return courseMapper.findCourseByCondition(courseVO);
    }

    @Override
    public void saveCourseOrTeacher(CourseVO courseVO){
        //1. 封装课程信息
        Course course = new Course();
        try {
            BeanUtils.copyProperties(course, courseVO);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //2. 补全课程信息
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);
        course.setIsDel(0);
        
        //3. 调用insert方法新增记录, 获得course的id
        courseMapper.saveCourse(course);
        int id = course.getId();
        //4. 封装讲师信息
        Teacher teacher = new Teacher();
        try {
            BeanUtils.copyProperties(teacher, courseVO);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //5. 补全讲师信息
        teacher.setCourseId(id);
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        
        //6. 添加讲师信息
        courseMapper.saveTeacher(teacher);
    }

    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) {
        Course course = new Course();
        try {
            BeanUtils.copyProperties(course, courseVO);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Date date = new Date();
        course.setUpdateTime(date);
        courseMapper.updateCourse(course);
        int id = course.getId();
        Teacher teacher = new Teacher();
        try {
            BeanUtils.copyProperties(teacher, courseVO);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        teacher.setCourseId(id);
        teacher.setUpdateTime(date);
        courseMapper.updateTeacher(teacher);
    }

    @Override
    public CourseVO findCourseById(int id) {
        return courseMapper.findCourseById(id);
    }

    @Override
    public void updateCourseStatus(int id, int status) {
        Course course = new Course();
        course.setId(id);
        course.setStatus(status);
        Date date = new Date();
        course.setUpdateTime(date);
        courseMapper.updateCourseStatus(course);
    }


}
