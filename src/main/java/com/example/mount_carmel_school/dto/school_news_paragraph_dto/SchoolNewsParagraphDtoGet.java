package com.example.mount_carmel_school.dto.school_news_paragraph_dto;

import com.example.mount_carmel_school.dto.school_news_dto.SchoolNewsDtoGet;
import com.example.mount_carmel_school.dto.school_news_paragraph_photo_dto.SchoolNewsParagraphPhotoDtoGet;
import com.example.mount_carmel_school.enums.ParagraphPhotoSort;
import com.example.mount_carmel_school.model.SchoolNewsParagraph;
import com.example.mount_carmel_school.model.SchoolNewsParagraphPhoto;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class SchoolNewsParagraphDtoGet {
    private Long id;
    private String paragraph;
    private ParagraphPhotoSort paragraphPhotoSort;
    private SchoolNewsDtoGet schoolNews;
    private List<SchoolNewsParagraphPhotoDtoGet> schoolNewsParagraphPhotos = new ArrayList<>();
    private Date createdAt;
    private Date lastModifiedAt;
    public  SchoolNewsParagraphDtoGet(SchoolNewsParagraph schoolNewsParagraph)
    {
        BeanUtils.copyProperties(schoolNewsParagraph,this,"schoolNews");

        this.schoolNews = new SchoolNewsDtoGet(schoolNewsParagraph.getSchoolNews());

        if(schoolNewsParagraph.getSchoolNewsParagraphPhotos() != null)
        {
            for(SchoolNewsParagraphPhoto item:schoolNewsParagraph.getSchoolNewsParagraphPhotos())
            {
                schoolNewsParagraphPhotos.add(new SchoolNewsParagraphPhotoDtoGet(item,"PARAGRAPH"));
            }
        }

    }

    public  SchoolNewsParagraphDtoGet(SchoolNewsParagraph schoolNewsParagraph,String ignore)
    {
        BeanUtils.copyProperties(schoolNewsParagraph,this,"schoolNews");

        if(!ignore.equals("PHOTO"))
        {
            if(schoolNewsParagraph.getSchoolNewsParagraphPhotos() != null)
            {
                for(SchoolNewsParagraphPhoto item:schoolNewsParagraph.getSchoolNewsParagraphPhotos())
                {
                    schoolNewsParagraphPhotos.add(new SchoolNewsParagraphPhotoDtoGet(item,"PARAGRAPH"));
                }
            }
        }

    }
}
