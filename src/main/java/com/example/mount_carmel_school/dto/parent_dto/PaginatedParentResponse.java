package com.example.mount_carmel_school.dto.parent_dto;

import com.example.mount_carmel_school.dto.UserDto.UserDtoGet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedParentResponse {
    private List<ParentDtoGet> parents;
    private Long numberOfItems;
    private int numberOfPages;
}
