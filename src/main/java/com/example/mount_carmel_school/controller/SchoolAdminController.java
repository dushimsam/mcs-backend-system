package com.example.mount_carmel_school.controller;
import com.example.mount_carmel_school.dto.school_admin_dto.SchoolAdminDtoGet;
import com.example.mount_carmel_school.dto.school_admin_dto.SchoolAdminDtoPost;
import com.example.mount_carmel_school.service.SchoolAdminService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/school-admins")
@AllArgsConstructor

public class SchoolAdminController {
    private final SchoolAdminService schoolAdminService;

    @GetMapping
    public List<SchoolAdminDtoGet> getAll() {
        return schoolAdminService.getAll();
    }

    @PostMapping
    public SchoolAdminDtoGet add(@RequestBody SchoolAdminDtoPost userDtoPost)  {
        return schoolAdminService.add(userDtoPost);
    }

    @GetMapping(path = "{id}")
    public SchoolAdminDtoGet get(
            @PathVariable("id") Long id) {
        return   schoolAdminService.get(id);
    }
}
