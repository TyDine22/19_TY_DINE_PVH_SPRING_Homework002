package com.example._19_ty_dine_pvh_springboot_homework002.controller;

import com.example._19_ty_dine_pvh_springboot_homework002.model.entity.Student;
import com.example._19_ty_dine_pvh_springboot_homework002.model.request.StudentRequest;
import com.example._19_ty_dine_pvh_springboot_homework002.model.response.ApiResponse;
import com.example._19_ty_dine_pvh_springboot_homework002.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        List<Student> students = studentService.getAllStudents(page, size);
        ApiResponse<List<Student>> response = ApiResponse.<List<Student>>builder().success(true).status(HttpStatus.OK.value()).message("Students retrieved successfully").payload(students).timestamps(Instant.now()).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable("student-id") Long studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            ApiResponse<Student> response = ApiResponse.<Student>builder().success(false).status(HttpStatus.NOT_FOUND.value()).message("No students found with the given ID").payload(student).timestamps(Instant.now()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse<Student> response = ApiResponse.<Student>builder().success(true).status(HttpStatus.OK.value()).message("Student fetched successfully").payload(student).timestamps(Instant.now()).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<Student>> addStudent(@RequestBody StudentRequest request) {
        Student student = studentService.addStudent(request);
        if(student == null) {
            ApiResponse<Student> response = ApiResponse.<Student>builder().success(false).status(HttpStatus.NOT_FOUND.value()).message("No courses found with the given ID").payload(student).timestamps(Instant.now()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse<Student> response = ApiResponse.<Student>builder().success(true).status(HttpStatus.OK.value()).message("Student created successfully").payload(student).timestamps(Instant.now()).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> updateStudentById(@PathVariable("student-id") Long studentId, @RequestBody StudentRequest request) {
        Student student = studentService.updateStudentById(studentId, request);
        if (student == null) {
            String error = studentService.getError();
            String message;

            if ("student_not_found".equals(error)) {
                message = "No students found with the given IDID";
            } else if ("course_not_found".equals(error)) {
                message = "No courses found with the given ID";
            } else {
                message = "Unknown error";
            }

            ApiResponse<Student> response = ApiResponse.<Student>builder().success(false).status(HttpStatus.NOT_FOUND.value()).message(message).payload(student).timestamps(Instant.now()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        ApiResponse<Student> response = ApiResponse.<Student>builder().success(true).status(HttpStatus.OK.value()).message("Student updated successfully").payload(student).timestamps(Instant.now()).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{student-id}")
    public ResponseEntity<ApiResponse<?>> deleteStudentById(@PathVariable("student-id") Long studentId) {
        boolean deleted = studentService.deleteStudentById(studentId);

        if(deleted) {
            ApiResponse<Student> response = ApiResponse.<Student>builder().success(true).status(HttpStatus.OK.value()).message("Student deleted successfully").timestamps(Instant.now()).build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        ApiResponse<Student> response = ApiResponse.<Student>builder().success(false).status(HttpStatus.NOT_FOUND.value()).message("No students found with the given ID").timestamps(Instant.now()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
