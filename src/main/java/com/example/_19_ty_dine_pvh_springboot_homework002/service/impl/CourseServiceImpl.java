package com.example._19_ty_dine_pvh_springboot_homework002.service.impl;

import com.example._19_ty_dine_pvh_springboot_homework002.model.entity.Course;
import com.example._19_ty_dine_pvh_springboot_homework002.model.entity.Instructor;
import com.example._19_ty_dine_pvh_springboot_homework002.model.request.CourseRequest;
import com.example._19_ty_dine_pvh_springboot_homework002.repository.CourseRepository;
import com.example._19_ty_dine_pvh_springboot_homework002.repository.InstructorRepository;
import com.example._19_ty_dine_pvh_springboot_homework002.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private String error;
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    public CourseServiceImpl(CourseRepository courseRepository, InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    @Override
    public String getError() {
        return error;
    }

    @Override
    public List<Course> getAllCourses(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        return courseRepository.getAllCourse(offset, size);
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.getCourseById(courseId);
    }

    @Override
    public Course addCourse(CourseRequest request) {
        Instructor instructor = instructorRepository.getInstructorById(request.getInstructorId());
        if(instructor == null) {
            return null;
        }
        return courseRepository.addCourse(request);
    }

    @Override
    public Course updateCourseById(Long courseId, CourseRequest request) {
        error = null;

        Course course = courseRepository.getCourseById(courseId);
        if(course == null) {
            error = "course_not_found";
            return null;
        }

        Instructor instructor = instructorRepository.getInstructorById(request.getInstructorId());
        if(instructor == null) {
            error = "instructor_not_found";
            return null;
        }
        return courseRepository.updateCourseById(courseId, request);
    }

    @Override
    public boolean deleteCourseById(Long courseId) {
        return courseRepository.deleteCourseById(courseId);
    }
}
