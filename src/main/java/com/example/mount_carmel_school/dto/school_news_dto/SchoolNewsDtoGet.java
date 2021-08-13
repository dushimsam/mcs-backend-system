package com.example.mount_carmel_school.dto.school_news_dto;

import com.example.mount_carmel_school.dto.school_employee_dto.SchoolEmployeeDtoGet;
import com.example.mount_carmel_school.dto.school_news_paragraph_dto.SchoolNewsParagraphDtoGet;
import com.example.mount_carmel_school.model.SchoolEmployee;
import com.example.mount_carmel_school.model.SchoolNews;
import com.example.mount_carmel_school.model.SchoolNewsParagraph;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class SchoolNewsDtoGet {
    private Long id;
    private String title;
    private String mainPicPath;
    private Date createdAt;
    private Date lastModifiedAt;
    private String paragraphs;
    private SchoolEmployeeDtoGet postedBy;
    public SchoolNewsDtoGet(SchoolNews schoolNews)
    {
        BeanUtils.copyProperties(schoolNews,this,"postedBy");
        this.postedBy = new SchoolEmployeeDtoGet(schoolNews.getSchoolEmployee());
    }


}
