package com.example.mount_carmel_school.controller;
import com.example.mount_carmel_school.dto.parent_dto.ParentDtoGet;
import com.example.mount_carmel_school.dto.school_employee_dto.SchoolEmployeeDtoGet;
import com.example.mount_carmel_school.dto.school_employee_dto.SchoolEmployeeDtoPost;
import com.example.mount_carmel_school.service.SchoolEmployeeService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/school-employees")
@AllArgsConstructor

public class SchoolEmployeeController {
    private final SchoolEmployeeService schoolEmployeeService;

    @GetMapping
    public ResponseEntity<List<SchoolEmployeeDtoGet>> getAll() {
        return new ResponseEntity<>(schoolEmployeeService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SchoolEmployeeDtoGet> add(@RequestBody SchoolEmployeeDtoPost schoolEmployeeDtoPost)  {
        return new ResponseEntity<>(schoolEmployeeService.add(schoolEmployeeDtoPost),HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    public  ResponseEntity<SchoolEmployeeDtoGet> get(
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(schoolEmployeeService.get(id), HttpStatus.OK);
    }

    @GetMapping(path = "user/{UserId}")
    public  ResponseEntity<SchoolEmployeeDtoGet> getByUser(
            @PathVariable("UserId") Long id) {
        return new ResponseEntity<>(schoolEmployeeService.getByUser(id), HttpStatus.OK);
    }

    @GetMapping(path = "/status/{status}")
    public ResponseEntity<List<SchoolEmployeeDtoGet>> getAllByStatus(@PathVariable("status") String status) {
        return new ResponseEntity<>(schoolEmployeeService.getByStatus(status), HttpStatus.OK);
    }
}
