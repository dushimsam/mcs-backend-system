package com.example.mount_carmel_school.dto.school_employee_dto;

import com.example.mount_carmel_school.dto.UserDto.UserDtoGet;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginatedSchoolEmployeeResponse {
    private List<SchoolEmployeeDtoGet> employees;
    private Long numberOfItems;
    private int numberOfPages;
}
