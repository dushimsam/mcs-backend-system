package com.example.mount_carmel_school.dto.school_news_dto;

import com.example.mount_carmel_school.dto.school_news_paragraph_dto.SchoolNewsParagraphDtoGet;
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
    private List<SchoolNewsParagraphDtoGet> schoolNewsParagraphs = new ArrayList<>();
    private Date createdAt;
    private Date lastModifiedAt;
    public SchoolNewsDtoGet(SchoolNews schoolNews)
    {
        BeanUtils.copyProperties(schoolNews,this,"schoolNewsParagraphs");

        if(schoolNews.getSchoolNewsParagraphs() != null)
        {
            for(SchoolNewsParagraph item:schoolNews.getSchoolNewsParagraphs())
            {
                this.schoolNewsParagraphs.add(new SchoolNewsParagraphDtoGet(item,"NEWS"));
            }

        }

    }

    public SchoolNewsDtoGet(SchoolNews schoolNews,String ignore)
    {
        BeanUtils.copyProperties(schoolNews,this,"schoolNewsParagraphs");

        if(!ignore.equals("PARAGRAPH")){
            if(schoolNews.getSchoolNewsParagraphs() != null)
            {
                for(SchoolNewsParagraph item:schoolNews.getSchoolNewsParagraphs())
                {
                    this.schoolNewsParagraphs.add(new SchoolNewsParagraphDtoGet(item,"NEWS"));
                }

            }
        }


    }
}
