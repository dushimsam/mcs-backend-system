package com.example.mount_carmel_school.dto;

import com.example.mount_carmel_school.dto.parent_dto.ParentDtoGet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResponseDto {
    private List<?> docs = new ArrayList<>();
    private int page;
    private Long totalItems;
    private int totalPages;
}
