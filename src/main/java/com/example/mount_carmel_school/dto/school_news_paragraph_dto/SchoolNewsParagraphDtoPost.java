package com.example.mount_carmel_school.dto.school_news_paragraph_dto;

import com.example.mount_carmel_school.enums.ParagraphPhotoSort;
import lombok.Data;

@Data
public class SchoolNewsParagraphDtoPost {
    private Long schoolNewsId;
    private String paragraph;
    private ParagraphPhotoSort paragraphPhotoSort=ParagraphPhotoSort.PIC_LAST;
}
