package com.example._19_ty_dine_pvh_springboot_homework002.repository;

import com.example._19_ty_dine_pvh_springboot_homework002.model.entity.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentCourseRepository {

    @Results(id = "studentCourseMapper", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "instructor", column = "instructor_id", one = @One(select = "com.example._19_ty_dine_pvh_springboot_homework002.repository.InstructorRepository.getInstructorById"))
    })
    @Select("""
    SELECT * FROM student_course sc
            INNER JOIN courses c 
                    ON sc.course_id = c.course_id
    WHERE student_id = #{studentId};
""")
    public List<Course> getCoursesByStudentId(Long studentId);

    @ResultMap("studentCourseMapper")
    @Insert("""
        INSERT INTO student_course VALUES (#{studentId}, #{courseId});
""")
    void addStudentAndCourse(Long studentId, Long courseId);

    @ResultMap("studentCourseMapper")
    @Delete("""
        DELETE FROM student_course WHERE student_id = #{studentId};
""")
    void deleteStudentCourseById(Long studentId);
}
