package com.example.mount_carmel_school.dto.school_news_photos_dto;

import com.example.mount_carmel_school.dto.school_employee_dto.SchoolEmployeeDtoGet;
import com.example.mount_carmel_school.model.SchoolNews;
import com.example.mount_carmel_school.model.global.SchoolNewsPhoto;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class SchoolNewsPhotoDtoGet {
    private Long id;
    private String url;
    private Date createdAt;
    private Date lastModifiedAt;
    public SchoolNewsPhotoDtoGet(SchoolNewsPhoto schoolNewsPhoto)
    {
        BeanUtils.copyProperties(schoolNewsPhoto,this);
    }
}
