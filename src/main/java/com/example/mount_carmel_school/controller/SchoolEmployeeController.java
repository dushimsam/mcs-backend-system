package com.example.mount_carmel_school.controller;
import com.example.mount_carmel_school.dto.school_employee_dto.SchoolEmployeeDtoGet;
import com.example.mount_carmel_school.dto.school_employee_dto.SchoolEmployeeDtoPost;
import com.example.mount_carmel_school.service.SchoolEmployeeService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/school-employees")
@AllArgsConstructor

public class SchoolEmployeeController {
    private final SchoolEmployeeService schoolEmployeeService;

    @GetMapping
    public List<SchoolEmployeeDtoGet> getAll() {
        return schoolEmployeeService.getAll();
    }

    @PostMapping
    public SchoolEmployeeDtoGet add(@RequestBody SchoolEmployeeDtoPost schoolEmployeeDtoPost)  {
        return schoolEmployeeService.add(schoolEmployeeDtoPost);
    }

    @GetMapping(path = "{id}")
    public SchoolEmployeeDtoGet get(
            @PathVariable("id") Long id) {
        return   schoolEmployeeService.get(id);
    }

//    @GetMapping(path = "/school/{schoolId}")
//    public List<SchoolEmployeeDtoGet>  getFromSchool(
//            @PathVariable("schoolId") Long schoolId) {
//        return   schoolEmployeeService.getFromSchool(schoolId);
//    }
}
