package com.example.mount_carmel_school.controller;

import com.example.mount_carmel_school.dto.student_dto.StudentDtoGet;
import com.example.mount_carmel_school.dto.student_dto.StudentDtoPost;
import com.example.mount_carmel_school.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<StudentDtoGet> getAll() {
        return studentService.getAll();
    }

    @PostMapping
    public StudentDtoGet add(@RequestBody StudentDtoPost studentDtoPost)  {
        return studentService.add(studentDtoPost);
    }

    @GetMapping(path = "{id}")
    public StudentDtoGet get(
            @PathVariable("id") Long id) {
        return   studentService.get(id);
    }
//    @PutMapping(path="/student/{studentId}/parent/{parentId}")
//    public StudentDtoGet addParentToStudent(
//            @PathVariable("studentId") Long studentId, @PathVariable("parentId") Long parentId) {
//        return   studentService.addParentToStudent(studentId,parentId);
//    }

//    @GetMapping(path = "/school/{schoolId}")
//    public  List<StudentDtoGet> studentsFromSchool(
//            @PathVariable("schoolId") Long schoolId) {
//        return   studentService.studentsFromSchool(schoolId);
//    }
}
