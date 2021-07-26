package com.example.mount_carmel_school.controller;

import com.example.mount_carmel_school.dto.DeleteResponseDto;
import com.example.mount_carmel_school.dto.student_dto.StudentDtoGet;
import com.example.mount_carmel_school.dto.student_dto.StudentDtoPost;
import com.example.mount_carmel_school.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDtoGet>> getAll() {
        return new ResponseEntity<List<StudentDtoGet>>(studentService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDtoGet> add(@RequestBody StudentDtoPost studentDtoPost)  {
        return new ResponseEntity<StudentDtoGet>(studentService.add(studentDtoPost),HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<StudentDtoGet> get(
            @PathVariable("id") Long id) {
        return  new ResponseEntity<>(studentService.get(id),HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<StudentDtoGet> update(
            @PathVariable("id") Long id,@RequestBody StudentDtoPost studentDtoPost) {
        return  new ResponseEntity<StudentDtoGet>(studentService.update(id,studentDtoPost),HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<DeleteResponseDto> delete(
            @PathVariable("id") Long id) {
        return  new ResponseEntity<DeleteResponseDto>(studentService.delete(id),HttpStatus.OK);
    }
}
