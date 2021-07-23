package com.example.mount_carmel_school.dto.school_news_dto;

import com.example.mount_carmel_school.dto.school_news_paragraph_dto.SchoolNewsParagraphDtoGet;
import com.example.mount_carmel_school.model.SchoolNews;
import com.example.mount_carmel_school.model.SchoolNewsParagraph;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class SchoolNewsDtoGet {
    private Long id;
    private String title;
    private String mainPicPath;
    private List<SchoolNewsParagraphDtoGet> schoolNewsParagraphs;
    public SchoolNewsDtoGet(SchoolNews schoolNews)
    {
        BeanUtils.copyProperties(schoolNews,this,"schoolNewsParagraphs");
        for(SchoolNewsParagraph item:schoolNews.getSchoolNewsParagraphs())
        {
            this.schoolNewsParagraphs.add(new SchoolNewsParagraphDtoGet(item));
        }

    }
}
