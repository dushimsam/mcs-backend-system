package com.example.mount_carmel_school.dto.school_news_paragraph_photo_dto;

import com.example.mount_carmel_school.dto.school_news_paragraph_dto.SchoolNewsParagraphDtoGet;
import com.example.mount_carmel_school.model.SchoolNewsParagraphPhoto;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class SchoolNewsParagraphPhotoDtoGet {
    private Long id;
    private String photoPath;
    private SchoolNewsParagraphDtoGet schoolNewsParagraph;
    private Date createdAt;
    private Date lastModifiedAt;
    public SchoolNewsParagraphPhotoDtoGet(SchoolNewsParagraphPhoto schoolNewsParagraphPhoto)
    {
        BeanUtils.copyProperties(schoolNewsParagraphPhoto,this,"schoolNewsParagraph");
        this.schoolNewsParagraph = new SchoolNewsParagraphDtoGet(schoolNewsParagraphPhoto.getSchoolNewsParagraph());
    }

    public SchoolNewsParagraphPhotoDtoGet(SchoolNewsParagraphPhoto schoolNewsParagraphPhoto,String ignore)
    {
        BeanUtils.copyProperties(schoolNewsParagraphPhoto,this,"schoolNewsParagraph");
        if(!ignore.equals("PARAGRAPH"))
        {
            this.schoolNewsParagraph = new SchoolNewsParagraphDtoGet(schoolNewsParagraphPhoto.getSchoolNewsParagraph(),"PHOTO");
        }
    }
}
