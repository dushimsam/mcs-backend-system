package com.example.mount_carmel_school.dto.auth_dto;
import lombok.Data;

@Data
public class AuthResponse {
    private String token;
  public AuthResponse(String token)
    {
        this.token =token;
    }
}
