package com.example.mount_carmel_school.dto.school_employee_dto;

import com.example.mount_carmel_school.dto.UserDto.UserDtoGet;
import com.example.mount_carmel_school.model.SchoolEmployee;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class SchoolEmployeeDtoGet {
    private  Long id;
    private String residence;
    private UserDtoGet user;

    public SchoolEmployeeDtoGet(SchoolEmployee employee){
        BeanUtils.copyProperties(employee,this,"user");
        this.user = new UserDtoGet(employee.getUser());
    }
}
