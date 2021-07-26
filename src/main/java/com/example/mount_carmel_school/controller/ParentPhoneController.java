package com.example.mount_carmel_school.controller;

import com.example.mount_carmel_school.dto.DeleteResponseDto;
import com.example.mount_carmel_school.dto.parent_phone.ParentPhoneDtoGet;
import com.example.mount_carmel_school.dto.parent_phone.ParentPhoneDtoPost;
import com.example.mount_carmel_school.service.ParentPhoneService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/parent-phones")
@AllArgsConstructor
public class ParentPhoneController {
    private final ParentPhoneService parentPhoneService;

    @GetMapping
    public ResponseEntity<List<ParentPhoneDtoGet>> getAll() {
        return new ResponseEntity<List<ParentPhoneDtoGet>>(parentPhoneService.getAll(),HttpStatus.OK);
    }


    @GetMapping(path="{id}")
    public ResponseEntity<ParentPhoneDtoGet> get(@PathVariable("id") Long id) {
        return new ResponseEntity<ParentPhoneDtoGet>(parentPhoneService.get(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ParentPhoneDtoGet> addPhoneToParent(@RequestBody ParentPhoneDtoPost parentPhonePost)  {
        return new ResponseEntity<ParentPhoneDtoGet>(parentPhoneService.addPhoneToParent(parentPhonePost),HttpStatus.CREATED);
    }


    @PutMapping(path="{id}")
    public ResponseEntity<ParentPhoneDtoGet> update(@PathVariable("id") Long id,@RequestBody ParentPhoneDtoPost parentPhonePost) {
        return new ResponseEntity<ParentPhoneDtoGet>(parentPhoneService.update(id,parentPhonePost),HttpStatus.OK);
    }

    @DeleteMapping(path="{id}")
    public ResponseEntity<DeleteResponseDto> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<DeleteResponseDto>(parentPhoneService.delete(id),HttpStatus.OK);
    }

}
