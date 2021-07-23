package com.example.mount_carmel_school.dto.school_admin_dto;
import com.example.mount_carmel_school.dto.UserDto.UserDtoGet;
import com.example.mount_carmel_school.model.SchoolAdmin;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class SchoolAdminDtoGet {
    private Long id;
    private String residence;
    private UserDtoGet user;

    public SchoolAdminDtoGet(SchoolAdmin admin){
        BeanUtils.copyProperties(admin,this,"user");
        user = new UserDtoGet(admin.getUser());
    }
}
