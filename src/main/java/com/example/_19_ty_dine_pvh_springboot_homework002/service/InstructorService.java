package com.example._19_ty_dine_pvh_springboot_homework002.service;

import com.example._19_ty_dine_pvh_springboot_homework002.model.entity.Instructor;
import com.example._19_ty_dine_pvh_springboot_homework002.model.request.InstructorRequest;

import java.util.List;

public interface InstructorService {

    List<Instructor> getAllInstructors(Integer page, Integer size);

    Instructor getInstructorById(Long instructorId);

    Instructor addInstructor(InstructorRequest request);

    Instructor updateInstructorById(Long instructorId, InstructorRequest request);

    boolean deleteInstructorById(Long instructorId);
}
