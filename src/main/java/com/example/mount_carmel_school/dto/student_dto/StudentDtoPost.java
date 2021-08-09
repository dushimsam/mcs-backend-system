package com.example.mount_carmel_school.dto.student_dto;


import lombok.Data;

import java.util.List;


@Data
public class StudentDtoPost {
    private String studentCode;
    private Long parentId;
    private String studentNames;
    private String gender;
    private String studentClass;

}
