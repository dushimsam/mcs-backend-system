package com.example.mount_carmel_school.controller;
import com.example.mount_carmel_school.dto.school_admin_dto.SchoolAdminDtoGet;
import com.example.mount_carmel_school.dto.school_admin_dto.SchoolAdminDtoPost;
import com.example.mount_carmel_school.service.SchoolAdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/school-admins")
@AllArgsConstructor

public class SchoolAdminController {
    private final SchoolAdminService schoolAdminService;

    @GetMapping
    public ResponseEntity<List<SchoolAdminDtoGet>> getAll() {
        return new ResponseEntity<>(schoolAdminService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SchoolAdminDtoGet> add(@RequestBody SchoolAdminDtoPost userDtoPost)  {
        return new ResponseEntity<>(schoolAdminService.add(userDtoPost), HttpStatus.CREATED);
    }


    @GetMapping(path = "user/{UserId}")
    public ResponseEntity<SchoolAdminDtoGet> getByUser(
            @PathVariable("UserId") Long id) {
        return new ResponseEntity<>(schoolAdminService.getByUser(id), HttpStatus.OK);
    }


    @GetMapping(path = "{id}")
    public ResponseEntity<SchoolAdminDtoGet> get(
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(schoolAdminService.get(id), HttpStatus.OK);
    }
}
