package com.example._19_ty_dine_pvh_springboot_homework002.service.impl;

import com.example._19_ty_dine_pvh_springboot_homework002.model.entity.Instructor;
import com.example._19_ty_dine_pvh_springboot_homework002.model.request.InstructorRequest;
import com.example._19_ty_dine_pvh_springboot_homework002.repository.InstructorRepository;
import com.example._19_ty_dine_pvh_springboot_homework002.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;

    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> getAllInstructors(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        return instructorRepository.getAllInstructors(offset, size);

    }

    @Override
    public Instructor getInstructorById(Long instructorId) {
        return instructorRepository.getInstructorById(instructorId);
    }

    @Override
    public Instructor addInstructor(InstructorRequest request){
        return instructorRepository.addInstructor(request);
    }

    @Override
    public Instructor updateInstructorById(Long instructorId, InstructorRequest request) {
        return instructorRepository.updateInstructorById(instructorId, request);
    }

    @Override
    public boolean deleteInstructorById(Long instructorId) {
        return instructorRepository.deleteInstructorById(instructorId);
    }
}
