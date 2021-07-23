package com.example.mount_carmel_school.controller;

import com.example.mount_carmel_school.dto.parent_phone.ParentPhoneDtoGet;
import com.example.mount_carmel_school.dto.parent_phone.ParentPhoneDtoPost;
import com.example.mount_carmel_school.service.ParentPhoneService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/parent-phones")
@AllArgsConstructor
public class ParentPhoneController {
    private final ParentPhoneService parentPhoneService;

    @GetMapping
    public List<ParentPhoneDtoGet> getAll() {
        return parentPhoneService.getAll();
    }


    @PutMapping(path = "/parent/{parentId}")
    public ParentPhoneDtoGet addPhoneToParent(@RequestBody ParentPhoneDtoPost parentPhonePost, @PathVariable("parentId") Long parentId)  {
        return parentPhoneService.addPhoneToParent(parentPhonePost,parentId);
    }



}
