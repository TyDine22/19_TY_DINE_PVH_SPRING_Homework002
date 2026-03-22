package com.example._19_ty_dine_pvh_springboot_homework002.service.impl;

import com.example._19_ty_dine_pvh_springboot_homework002.model.entity.Course;
import com.example._19_ty_dine_pvh_springboot_homework002.model.entity.Student;
import com.example._19_ty_dine_pvh_springboot_homework002.model.request.StudentRequest;
import com.example._19_ty_dine_pvh_springboot_homework002.repository.CourseRepository;
import com.example._19_ty_dine_pvh_springboot_homework002.repository.StudentCourseRepository;
import com.example._19_ty_dine_pvh_springboot_homework002.repository.StudentRepository;
import com.example._19_ty_dine_pvh_springboot_homework002.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private String error;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentCourseRepository studentCourseRepository;
    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository, StudentCourseRepository studentCourseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.studentCourseRepository = studentCourseRepository;
    }

    @Override
    public String getError() {
        return error;
    }

    @Override
    public boolean deleteStudentById(Long studentId) {
        return studentRepository.deleteStudentById(studentId);
    }

    @Override
    public List<Student> getAllStudents(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        return studentRepository.getAllStudents(offset, size);
    }

    @Override
    public Student getStudentById(Long studentId) {
        return studentRepository.getStudentById(studentId);
    }

    @Override
    public Student addStudent(StudentRequest request) {
        for (Long courseId : request.getCourses()) {
            Course course = courseRepository.getCourseById(courseId);
            if (course == null) {
                return null;
            }
        }

        Student student = studentRepository.addStudent(request);
        for (Long courseId : request.getCourses()) {
            studentCourseRepository.addStudentAndCourse(student.getStudentId(), courseId);
        }

        return studentRepository.getStudentById(student.getStudentId());
    }

    @Override
    public Student updateStudentById(Long studentId, StudentRequest request) {
        error = null;
        Student student = studentRepository.getStudentById(studentId);
        if (student == null) {
            error = "student_not_found";
            return null;
        }

        for (Long courseId : request.getCourses()) {
            Course course = courseRepository.getCourseById(courseId);
            if (course == null) {
                error = "course_not_found";
                return null;
            }
        }

        studentCourseRepository.deleteStudentCourseById(student.getStudentId());
        for (Long courseId : request.getCourses()) {
            studentCourseRepository.addStudentAndCourse(student.getStudentId(), courseId);
        }

        return studentRepository.getStudentById(student.getStudentId());
    }
}