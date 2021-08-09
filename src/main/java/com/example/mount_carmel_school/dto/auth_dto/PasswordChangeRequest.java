package com.example.mount_carmel_school.dto.auth_dto;

import lombok.Data;

@Data
public class PasswordChangeRequest {
    private String current_password;
    private String new_password;
}
