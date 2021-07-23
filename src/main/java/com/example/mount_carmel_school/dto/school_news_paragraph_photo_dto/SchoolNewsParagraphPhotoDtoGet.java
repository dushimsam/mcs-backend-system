package com.example.mount_carmel_school.dto.school_news_paragraph_photo_dto;

import com.example.mount_carmel_school.dto.school_news_paragraph_dto.SchoolNewsParagraphDtoGet;
import com.example.mount_carmel_school.model.SchoolNewsParagraphPhoto;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class SchoolNewsParagraphPhotoDtoGet {
    private Long id;
    private String photoPath;
    private SchoolNewsParagraphDtoGet schoolNewsParagraph;
    public SchoolNewsParagraphPhotoDtoGet(SchoolNewsParagraphPhoto schoolNewsParagraphPhoto)
    {
        BeanUtils.copyProperties(schoolNewsParagraphPhoto,this,"schoolNewsParagraph");
        this.schoolNewsParagraph = new SchoolNewsParagraphDtoGet(schoolNewsParagraphPhoto.getSchoolNewsParagraph());
    }
}
