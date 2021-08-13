package com.example.mount_carmel_school.controller;

import com.example.mount_carmel_school.dto.DeleteResponseDto;
import com.example.mount_carmel_school.dto.school_news_photos_dto.SchoolNewsPhotoDtoGet;
import com.example.mount_carmel_school.dto.school_news_photos_dto.SchoolNewsPhotoDtoPost;
import com.example.mount_carmel_school.dto.student_dto.StudentDtoGet;
import com.example.mount_carmel_school.dto.student_dto.StudentDtoPost;
import com.example.mount_carmel_school.service.SchoolNewsPhotoService;
import com.example.mount_carmel_school.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/school-news-photo")
@AllArgsConstructor
public class SchoolNewsPhotoController {
    private final SchoolNewsPhotoService schoolNewsPhotoService;
    @PostMapping
    public ResponseEntity<SchoolNewsPhotoDtoGet> add(@RequestBody SchoolNewsPhotoDtoPost schoolNewsPhotoDtoPost)  {
        return new ResponseEntity<SchoolNewsPhotoDtoGet>(schoolNewsPhotoService.add(schoolNewsPhotoDtoPost), HttpStatus.CREATED);
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<SchoolNewsPhotoDtoGet> get(
            @PathVariable("id") Long id) {
        return  new ResponseEntity<>(schoolNewsPhotoService.get(id),HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<SchoolNewsPhotoDtoGet> update(
            @PathVariable("id") Long id,@RequestBody SchoolNewsPhotoDtoPost schoolNewsPhotoDtoPost) {
        return  new ResponseEntity<SchoolNewsPhotoDtoGet>(schoolNewsPhotoService.put(id,schoolNewsPhotoDtoPost),HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<DeleteResponseDto> delete(
            @PathVariable("id") Long id) {
        return  new ResponseEntity<DeleteResponseDto>(schoolNewsPhotoService.delete(id),HttpStatus.OK);
    }

}
