package com.example.mount_carmel_school.controller;


import com.example.mount_carmel_school.dto.DeleteResponseDto;

import com.example.mount_carmel_school.dto.school_news_paragraph_dto.SchoolNewsParagraphDtoPost;
import com.example.mount_carmel_school.dto.school_news_paragraph_photo_dto.SchoolNewsParagraphPhotoDtoGet;
import com.example.mount_carmel_school.dto.school_news_paragraph_photo_dto.SchoolNewsParagraphPhotoDtoPost;
import com.example.mount_carmel_school.service.SchoolNewsParagraphPhotoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/school-news-paragraph-photos")
@AllArgsConstructor
public class SchoolNewsParagraphPhotoController {
    private final SchoolNewsParagraphPhotoService schoolNewsParagraphPhotoService;


    @PostMapping
    public ResponseEntity<SchoolNewsParagraphPhotoDtoGet> add(@RequestBody SchoolNewsParagraphPhotoDtoPost photoPost)  {
        return new ResponseEntity<>(schoolNewsParagraphPhotoService.add(photoPost),HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<SchoolNewsParagraphPhotoDtoGet>> getAll() {
        return new  ResponseEntity<>(schoolNewsParagraphPhotoService.getAll(), HttpStatus.OK);
    }



    @GetMapping(path = "{id}")
    public ResponseEntity<SchoolNewsParagraphPhotoDtoGet> get(
            @PathVariable("id") Long id) {
        return   new ResponseEntity<>(schoolNewsParagraphPhotoService.get(id),HttpStatus.OK);
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<SchoolNewsParagraphPhotoDtoGet> update(
            @PathVariable("id") Long id,@RequestBody SchoolNewsParagraphPhotoDtoPost photoPost) {
        return   new ResponseEntity<>(schoolNewsParagraphPhotoService.update(id,photoPost),HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<DeleteResponseDto> delete(
            @PathVariable("id") Long id) {
        return   new ResponseEntity<>(schoolNewsParagraphPhotoService.delete(id),HttpStatus.OK);
    }


    @GetMapping(path="/paragraph/{parId}")
    public ResponseEntity<List<SchoolNewsParagraphPhotoDtoGet>> getByParagraph(@PathVariable("parId") Long id) {
        return new  ResponseEntity<>(schoolNewsParagraphPhotoService.getAllBySchoolNewsParagraph(id), HttpStatus.OK);
    }
}
