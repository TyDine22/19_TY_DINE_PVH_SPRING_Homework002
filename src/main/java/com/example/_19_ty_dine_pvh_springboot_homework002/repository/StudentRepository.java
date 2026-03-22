package com.example._19_ty_dine_pvh_springboot_homework002.repository;

import com.example._19_ty_dine_pvh_springboot_homework002.model.entity.Student;
import com.example._19_ty_dine_pvh_springboot_homework002.model.request.StudentRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentRepository {
    @Results(id = "studentMapper", value = {
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "studentName", column = "student_name"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "courses", column = "student_id", many = @Many(select = "com.example._19_ty_dine_pvh_springboot_homework002.repository.StudentCourseRepository.getCoursesByStudentId"))
    })
    @Select("""
        SELECT * FROM students OFFSET #{offset} LIMIT #{size};
""")
    List<Student> getAllStudents(Integer offset, Integer size);

    @ResultMap("studentMapper")
    @Select("""
        SELECT * FROM students WHERE student_id = #{studentId};
""")
    Student getStudentById(Long studentId);

    @ResultMap("studentMapper")
    @Select("""
        INSERT INTO students VALUES (default, #{req.studentName}, #{req.email}, #{req.phoneNumber}) RETURNING *;
""")
    Student addStudent(@Param("req") StudentRequest request);

    @ResultMap("studentMapper")
    @Delete("""
        DELETE FROM students WHERE student_id = #{studentId};
""")
    boolean deleteStudentById(Long studentId);
}
