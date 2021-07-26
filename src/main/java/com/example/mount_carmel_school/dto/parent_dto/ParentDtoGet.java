package com.example.mount_carmel_school.dto.parent_dto;

import com.example.mount_carmel_school.dto.UserDto.UserDtoGet;
import com.example.mount_carmel_school.dto.parent_phone.ParentPhoneDtoGet;
import com.example.mount_carmel_school.dto.student_dto.StudentDtoGet;
import com.example.mount_carmel_school.model.Parent;
import com.example.mount_carmel_school.model.ParentPhone;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ParentDtoGet {
    private Long id;
    private String residence;
    private UserDtoGet user;
    private List<StudentDtoGet> students = new ArrayList<>();
    private List<ParentPhoneDtoGet> phones = new ArrayList<>();
    private Date createdAt;
    private Date lastModifiedAt;
    public ParentDtoGet(Parent parent){
        BeanUtils.copyProperties(parent,this,"user");
        this.user = new UserDtoGet(parent.getUser());
        for(ParentPhone parentPhone: parent.getPhones())
        {
            this.phones.add(new ParentPhoneDtoGet(parentPhone));
        }
    }
}
