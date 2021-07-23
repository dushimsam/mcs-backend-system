package com.example.mount_carmel_school.dto.student_dto;


import lombok.Data;

import java.util.List;


@Data
public class StudentDtoPost {
    private String rfidCode;
    private Long userId;
    private List<Long> parents;
}
