package com.example.mount_carmel_school.dto.school_news_dto;

import com.example.mount_carmel_school.dto.UserDto.UserDtoGet;

import java.util.List;

public class PaginatedSchoolNewsResponse {
    private List<SchoolNewsDtoGet> schoolNewsList;
    private Long numberOfItems;
    private int numberOfPages;
}
