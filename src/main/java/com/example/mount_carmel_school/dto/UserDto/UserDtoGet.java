package com.example.mount_carmel_school.dto.UserDto;

import com.example.mount_carmel_school.enums.UserCategory;
import com.example.mount_carmel_school.model.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Data
public class UserDtoGet {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String profile;
    private String userName;
    private String gender;
    private String email;
    private UserCategory category;
    private Date createdAt;
    private Date lastModifiedAt;
    public UserDtoGet(User user){
        BeanUtils.copyProperties(user,this);
    }
}
