package com.example.mount_carmel_school.controller;

import com.example.mount_carmel_school.dto.parent_dto.ParentDtoGet;
import com.example.mount_carmel_school.dto.parent_dto.ParentDtoPost;
import com.example.mount_carmel_school.dto.school_admin_dto.SchoolAdminDtoGet;
import com.example.mount_carmel_school.exception.ApiRequestException;
import com.example.mount_carmel_school.exception.NotFoundException;
import com.example.mount_carmel_school.model.SchoolAdmin;
import com.example.mount_carmel_school.model.User;
import com.example.mount_carmel_school.service.ParentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/parents")
@AllArgsConstructor
public class ParentController {
    private final ParentService parentService;

    @GetMapping
    public ResponseEntity<List<ParentDtoGet>> getAll() {
        return new ResponseEntity<List<ParentDtoGet>>(parentService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ParentDtoGet> add(@RequestBody ParentDtoPost parentDtoPost)  {
        return new ResponseEntity<ParentDtoGet>(parentService.add(parentDtoPost),HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ParentDtoGet> get(
            @PathVariable("id") Long id) {
        return   new ResponseEntity<ParentDtoGet>(parentService.get(id),HttpStatus.OK);
    }

    @GetMapping(path = "/status/{status}")
    public ResponseEntity<List<ParentDtoGet>> getAllByStatus(@PathVariable("status") String status) {
        return new ResponseEntity<List<ParentDtoGet>>(parentService.getByStatus(status), HttpStatus.OK);
    }

    @PutMapping(path="/add-student/parent/{parentId}/student/{studentId}")
    public ResponseEntity<ParentDtoGet> addStudent(
            @PathVariable("parentId") Long parentId, @PathVariable("studentId") Long studentId) {
        return   new ResponseEntity<ParentDtoGet>(parentService.addStudentToParent(parentId,studentId),HttpStatus.OK);
    }



    @GetMapping(path = "user/{UserId}")
    public ResponseEntity<ParentDtoGet> getByUser(
            @PathVariable("UserId") Long id) {
        return  new ResponseEntity<ParentDtoGet>(parentService.getByUser(id),HttpStatus.OK);
    }

}
