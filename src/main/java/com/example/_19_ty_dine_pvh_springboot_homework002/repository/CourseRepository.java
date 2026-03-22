package com.example._19_ty_dine_pvh_springboot_homework002.repository;

import com.example._19_ty_dine_pvh_springboot_homework002.model.entity.Course;
import com.example._19_ty_dine_pvh_springboot_homework002.model.request.CourseRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseRepository {
    @Results(id="courseMapper", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "instructor", column = "instructor_id", one = @One(select = "com.example._19_ty_dine_pvh_springboot_homework002.repository.InstructorRepository.getInstructorById"))
    })
    @Select("""
        SELECT * FROM courses OFFSET #{offset} LIMIT #{size};
""")
    List<Course> getAllCourse(Integer offset, Integer size);

    @ResultMap("courseMapper")
    @Select("""
        SELECT * FROM courses WHERE course_id = #{courseId};
""")
    Course getCourseById(Long courseId);

    @ResultMap("courseMapper")
    @Select("""
        INSERT INTO courses VALUES (default, #{req.courseName}, #{req.description}, #{req.instructorId}) RETURNING *;
""")
    Course addCourse(@Param("req") CourseRequest request);

    @ResultMap("courseMapper")
    @Select("""
        UPDATE courses SET course_name = #{req.courseName}, description = #{req.description}, instructor_id = #{req.instructorId} WHERE course_id = #{courseId} RETURNING *;
""")
    Course updateCourseById(Long courseId, @Param("req")CourseRequest request);

    @ResultMap("courseMapper")
    @Delete("""
        DELETE FROM courses WHERE course_id = #{courseId};
""")
    boolean deleteCourseById(Long courseId);
}
