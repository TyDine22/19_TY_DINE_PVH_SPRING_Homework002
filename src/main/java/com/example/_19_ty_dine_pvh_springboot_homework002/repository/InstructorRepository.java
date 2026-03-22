package com.example._19_ty_dine_pvh_springboot_homework002.repository;

import com.example._19_ty_dine_pvh_springboot_homework002.model.entity.Instructor;
import com.example._19_ty_dine_pvh_springboot_homework002.model.request.InstructorRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InstructorRepository {
    @Results(id="instructorMapper", value = {
            @Result(property = "instructorId", column = "instructor_id"),
            @Result(property = "instructorName", column = "instructor_name")
    })
    @Select("""
        SELECT * FROM instructors OFFSET #{offset} LIMIT #{size};
""")
    List<Instructor> getAllInstructors(Integer offset, Integer size);

    @ResultMap("instructorMapper")
    @Select("""
        SELECT * FROM instructors WHERE instructor_id = #{instructorId};
""")
    Instructor getInstructorById(Long instructorId);

    @ResultMap("instructorMapper")
    @Select("""
        INSERT INTO instructors VALUES (default, #{req.instructorName}, #{req.email}) RETURNING *;
""")
    Instructor addInstructor(@Param("req") InstructorRequest request);

    @ResultMap("instructorMapper")
    @Select("""
        UPDATE instructors SET instructor_name = #{req.instructorName}, email = #{req.email} WHERE instructor_id = #{instructorId} RETURNING *;
""")
    Instructor updateInstructorById(Long instructorId, @Param("req") InstructorRequest request);

    @ResultMap("instructorMapper")
    @Delete("""
        DELETE FROM instructors WHERE instructor_id = #{instructorId};
""")
    boolean deleteInstructorById(Long instructorId);
}
