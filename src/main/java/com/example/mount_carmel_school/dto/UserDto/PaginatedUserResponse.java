package com.example.mount_carmel_school.dto.UserDto;

import com.example.mount_carmel_school.model.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginatedUserResponse {
    private List<UserDtoGet> users;
    private Long numberOfItems;
    private int numberOfPages;
}
