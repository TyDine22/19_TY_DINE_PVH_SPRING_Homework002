package com.example._19_ty_dine_pvh_springboot_homework002.model.request;

import com.example._19_ty_dine_pvh_springboot_homework002.model.entity.Course;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    private String studentName;
    @Schema(example = "example@gmail.com")
    private String email;
    private String phoneNumber;
    private List<Long> courses;
}
