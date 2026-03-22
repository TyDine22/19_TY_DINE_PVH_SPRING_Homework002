package com.example._19_ty_dine_pvh_springboot_homework002.controller;

import com.example._19_ty_dine_pvh_springboot_homework002.model.entity.Course;
import com.example._19_ty_dine_pvh_springboot_homework002.model.request.CourseRequest;
import com.example._19_ty_dine_pvh_springboot_homework002.model.response.ApiResponse;
import com.example._19_ty_dine_pvh_springboot_homework002.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        List<Course> courses = courseService.getAllCourses(page, size);
        ApiResponse<List<Course>> response = ApiResponse.<List<Course>>builder().success(true).status(HttpStatus.OK.value()).message("Courses fetched successfully").payload(courses).timestamps(Instant.now()).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable("course-id") Long courseId) {
        Course course = courseService.getCourseById(courseId);
        if (course == null) {
            ApiResponse<Course> response = ApiResponse.<Course>builder().success(false).status(HttpStatus.NOT_FOUND.value()).message("No courses found with the given ID").payload(course).timestamps(Instant.now()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse<Course> response = ApiResponse.<Course>builder().success(true).status(HttpStatus.OK.value()).message("Course fetched successfully").payload(course).timestamps(Instant.now()).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<Course>> addCourse(@RequestBody CourseRequest request) {
        Course course = courseService.addCourse(request);
        if (course == null) {
            ApiResponse<Course> response = ApiResponse.<Course>builder().success(false).status(HttpStatus.NOT_FOUND.value()).message("No instructors found with the given ID").payload(course).timestamps(Instant.now()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse<Course> response = ApiResponse.<Course>builder().success(true).status(HttpStatus.CREATED.value()).message("Course created successfully").payload(course).timestamps(Instant.now()).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> updateCourseById(@PathVariable("course-id") Long courseId, @RequestBody CourseRequest request) {
        Course course = courseService.updateCourseById(courseId, request);
        if (course == null) {
            String error = courseService.getError();
            String message;

            if ("course_not_found".equals(error)) {
                message = "No courses found with the given ID";
            } else if ("instructor_not_found".equals(error)) {
                message = "No instructors found with the given ID";
            } else {
                message = "Unknown error";
            }

            ApiResponse<Course> response = ApiResponse.<Course>builder().success(false).status(HttpStatus.NOT_FOUND.value()).message(message).payload(course).timestamps(Instant.now()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse<Course> response = ApiResponse.<Course>builder().success(true).status(HttpStatus.CREATED.value()).message("Course updated successfully").payload(course).timestamps(Instant.now()).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{course-id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable("course-id") Long courseId) {
        boolean deleted = courseService.deleteCourseById(courseId);
        if (deleted) {
            ApiResponse<Course> response = ApiResponse.<Course>builder().success(true).status(HttpStatus.OK.value()).message("Course deleted successfully").timestamps(Instant.now()).build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        ApiResponse<Course> response = ApiResponse.<Course>builder().success(false).status(HttpStatus.NOT_FOUND.value()).message("No courses found with the given ID").timestamps(Instant.now()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
