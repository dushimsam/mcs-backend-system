package com.example.mount_carmel_school.dto.parent_dto;
import com.example.mount_carmel_school.dto.parent_phone.ParentPhoneDtoPost;
import lombok.Data;

import java.util.List;

@Data
public class ParentDtoPost {
    private String residence;
    private Long userId;
    private String phone;
}
