package com.example._19_ty_dine_pvh_springboot_homework002.service;

import com.example._19_ty_dine_pvh_springboot_homework002.model.entity.Course;
import com.example._19_ty_dine_pvh_springboot_homework002.model.request.CourseRequest;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourses(Integer page, Integer size);

    Course getCourseById(Long courseId);
    Course addCourse(CourseRequest request);

    Course updateCourseById(Long courseId, CourseRequest request);
    String getError();

    boolean deleteCourseById(Long courseId);
}
