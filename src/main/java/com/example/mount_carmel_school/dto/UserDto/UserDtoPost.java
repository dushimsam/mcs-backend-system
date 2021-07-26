package com.example.mount_carmel_school.dto.UserDto;
import com.example.mount_carmel_school.enums.UserCategory;
import lombok.Data;

@Data
public class UserDtoPost {
    private String firstName;
    private String lastName;
    private String phone;
    private String profile;
    private String userName;
    private String password;
    private String gender;
    private String email;
    private UserCategory category;
}
