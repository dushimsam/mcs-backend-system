package com.example.mount_carmel_school.dto.school_news_dto;
import lombok.Data;

import java.util.List;

@Data
public class SchoolNewsDtoPost {
    private String title;
    private Long postedBy_employeeId;
    private String mainPicPath;
}
