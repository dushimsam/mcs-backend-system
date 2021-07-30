package com.example.mount_carmel_school.dto.auth_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeResponse {
    private String message;
    private Object user;
}
