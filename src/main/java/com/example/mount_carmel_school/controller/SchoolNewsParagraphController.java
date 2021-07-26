package com.example.mount_carmel_school.controller;


import com.example.mount_carmel_school.dto.DeleteResponseDto;
import com.example.mount_carmel_school.dto.school_news_paragraph_dto.SchoolNewsParagraphDtoGet;
import com.example.mount_carmel_school.dto.school_news_paragraph_dto.SchoolNewsParagraphDtoPost;
import com.example.mount_carmel_school.service.SchoolNewsParagraphService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/school-news-paragraphs")
@AllArgsConstructor
public class SchoolNewsParagraphController {
    private final SchoolNewsParagraphService schoolNewsParagraphService;

    @GetMapping
    public ResponseEntity<List<SchoolNewsParagraphDtoGet>> getAll() {
        return new  ResponseEntity<>(schoolNewsParagraphService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path="/school-news/{schoolNewsId}")
    public ResponseEntity<List<SchoolNewsParagraphDtoGet>> getAllBySchoolNews(@PathVariable("schoolNewsId") Long schoolNewsId) {
        return new  ResponseEntity<>(schoolNewsParagraphService.getAllBySchoolNews(schoolNewsId), HttpStatus.OK);
    }


    @GetMapping(path = "{id}")
    public ResponseEntity<SchoolNewsParagraphDtoGet> get(
            @PathVariable("id") Long id) {
        return   new ResponseEntity<SchoolNewsParagraphDtoGet>(schoolNewsParagraphService.get(id),HttpStatus.OK);
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<SchoolNewsParagraphDtoGet> update(
            @PathVariable("id") Long id,@RequestBody SchoolNewsParagraphDtoPost parentMessageDtoPost) {
        return   new ResponseEntity<>(schoolNewsParagraphService.update(id,parentMessageDtoPost),HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<DeleteResponseDto> delete(
            @PathVariable("id") Long id) {
        return   new ResponseEntity<DeleteResponseDto>(schoolNewsParagraphService.delete(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SchoolNewsParagraphDtoGet> add(@RequestBody SchoolNewsParagraphDtoPost schoolNewsDtoPost)  {
        return new ResponseEntity<SchoolNewsParagraphDtoGet>(schoolNewsParagraphService.add(schoolNewsDtoPost),HttpStatus.CREATED);
    }

}
