package com.example._19_ty_dine_pvh_springboot_homework002.service;

import com.example._19_ty_dine_pvh_springboot_homework002.model.entity.Student;
import com.example._19_ty_dine_pvh_springboot_homework002.model.request.StudentRequest;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents(Integer page, Integer size);
    Student getStudentById(Long studentId);
    Student addStudent(StudentRequest request);

    Student updateStudentById(Long studentId, StudentRequest request);

    String getError();

    boolean deleteStudentById(Long studentId);
}
