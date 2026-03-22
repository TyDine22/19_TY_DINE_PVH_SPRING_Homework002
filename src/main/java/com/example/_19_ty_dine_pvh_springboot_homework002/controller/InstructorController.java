package com.example._19_ty_dine_pvh_springboot_homework002.controller;

import com.example._19_ty_dine_pvh_springboot_homework002.model.entity.Instructor;
import com.example._19_ty_dine_pvh_springboot_homework002.model.request.InstructorRequest;
import com.example._19_ty_dine_pvh_springboot_homework002.model.response.ApiResponse;
import com.example._19_ty_dine_pvh_springboot_homework002.service.InstructorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
public class InstructorController {
    private final InstructorService instructorService;


    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @Operation(summary = "Get alll instructors")
    @GetMapping()
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllInstructors(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        List<Instructor> instructors = instructorService.getAllInstructors(page, size);
        ApiResponse<List<Instructor>> response = ApiResponse.<List<Instructor>>builder().success(true).status(HttpStatus.OK.value()).message("Instructors fetched successfully").payload(instructors).timestamps(Instant.now()).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Get instructor by ID")
    @GetMapping("/{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> getInstructorById(@PathVariable("instructor-id") Long instructorId) {
        Instructor instructor = instructorService.getInstructorById(instructorId);
        if (instructor == null) {
            ApiResponse<Instructor> response = ApiResponse.<Instructor>builder().success(false).status(HttpStatus.NOT_FOUND.value()).message("No instructors found with the given ID").payload(instructor).timestamps(Instant.now()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder().success(true).status(HttpStatus.OK.value()).message("Instructor fetched successfully").payload(instructor).timestamps(Instant.now()).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Create a new instructor")
    @PostMapping()
    public ResponseEntity<ApiResponse<Instructor>> addInstructor(@RequestBody InstructorRequest request) {
        Instructor instructor = instructorService.addInstructor(request);
        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder().success(true).status(HttpStatus.CREATED.value()).message("Instructor created successfully").payload(instructor).timestamps(Instant.now()).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update instructor by ID")
    @PutMapping("/{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> updateInstructorById(@PathVariable("instructor-id") Long instructorId, @RequestBody InstructorRequest request) {
        Instructor instructor = instructorService.updateInstructorById(instructorId, request);
        if (instructor == null) {
            ApiResponse<Instructor> response = ApiResponse.<Instructor>builder().success(false).status(HttpStatus.NOT_FOUND.value()).message("No instructors found with the given ID").payload(instructor).timestamps(Instant.now()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder().success(true).status(HttpStatus.OK.value()).message("Instructor updated successfully").payload(instructor).timestamps(Instant.now()).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Delete instructor by ID")
    @DeleteMapping("/{instructor-id}")
    public ResponseEntity<?> deleteInstructorById(@PathVariable("instructor-id") Long instructorId) {
        boolean deleted = instructorService.deleteInstructorById(instructorId);
        if (deleted) {
            ApiResponse<Instructor> response = ApiResponse.<Instructor>builder().success(true).status(HttpStatus.OK.value()).message("Instructor deleted successfully").timestamps(Instant.now()).build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder().success(false).status(HttpStatus.NOT_FOUND.value()).message("No instructors found with the given ID").timestamps(Instant.now()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}