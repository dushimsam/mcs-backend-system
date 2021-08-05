package com.example.mount_carmel_school.dto.parent_message_dto;

import com.example.mount_carmel_school.dto.school_employee_dto.SchoolEmployeeDtoGet;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginatedParentMessageResponse {
    private List<ParentMessageDtoGet> messages;
    private Long numberOfItems;
    private int numberOfPages;
}
