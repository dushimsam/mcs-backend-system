package com.example.mount_carmel_school.controller;

import com.example.mount_carmel_school.dto.DeleteResponseDto;
import com.example.mount_carmel_school.dto.school_news_dto.SchoolNewsDtoGet;
import com.example.mount_carmel_school.dto.school_news_dto.SchoolNewsDtoPost;
import com.example.mount_carmel_school.service.SchoolNewsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/school-news")
@AllArgsConstructor
public class SchoolNewsController {
    private final SchoolNewsService schoolNewsService;

    @GetMapping
    public ResponseEntity<List<SchoolNewsDtoGet>> getAll() {
        return new  ResponseEntity<>(schoolNewsService.getAll(), HttpStatus.OK);
    }


    @GetMapping(path = "{id}")
    public ResponseEntity<SchoolNewsDtoGet> get(
            @PathVariable("id") Long id) {
        return   new ResponseEntity<SchoolNewsDtoGet>(schoolNewsService.get(id),HttpStatus.OK);
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<SchoolNewsDtoGet> update(
            @PathVariable("id") Long id,@RequestBody SchoolNewsDtoPost parentMessageDtoPost) {
        return   new ResponseEntity<>(schoolNewsService.update(id,parentMessageDtoPost),HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<DeleteResponseDto> delete(
            @PathVariable("id") Long id) {
        return   new ResponseEntity<DeleteResponseDto>(schoolNewsService.delete(id),HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<SchoolNewsDtoGet> add(@RequestBody SchoolNewsDtoPost schoolNewsDtoPost)  {
        return new ResponseEntity<SchoolNewsDtoGet>(schoolNewsService.add(schoolNewsDtoPost),HttpStatus.CREATED);
    }



}
