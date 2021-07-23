package com.example.mount_carmel_school.dto.school_news_paragraph_photo_dto;

import com.example.mount_carmel_school.dto.school_news_paragraph_dto.SchoolNewsParagraphDtoGet;
import com.example.mount_carmel_school.model.SchoolNewsParagraphPhoto;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class SchoolNewsParagraphPhotoDtoPost {
    private Long schoolNewsParagraphId;
    private String photoPath;
}
