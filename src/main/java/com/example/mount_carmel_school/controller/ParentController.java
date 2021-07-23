package com.example.mount_carmel_school.controller;

import com.example.mount_carmel_school.dto.parent_dto.ParentDtoGet;
import com.example.mount_carmel_school.dto.parent_dto.ParentDtoPost;
import com.example.mount_carmel_school.service.ParentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/parents")
@AllArgsConstructor
public class ParentController {
    private final ParentService parentService;

    @GetMapping
    public List<ParentDtoGet> getAll() {
        return parentService.getAll();
    }

    @PostMapping
    public ParentDtoGet add(@RequestBody ParentDtoPost parentDtoPost)  {
        return parentService.add(parentDtoPost);
    }

    @GetMapping(path = "{id}")
    public ParentDtoGet get(
            @PathVariable("id") Long id) {
        return   parentService.get(id);
    }

}
