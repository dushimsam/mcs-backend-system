package com.example.mount_carmel_school.dto.student_dto;

import com.example.mount_carmel_school.dto.UserDto.UserDtoGet;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginatedStudentResponse {
    private List<StudentDtoGet> students;
    private Long numberOfItems;
    private int numberOfPages;
}
