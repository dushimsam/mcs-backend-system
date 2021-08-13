package com.example.mount_carmel_school.controller;

import com.example.mount_carmel_school.dto.DeleteResponseDto;
import com.example.mount_carmel_school.dto.parent_message_dto.ParentMessageDtoGet;
import com.example.mount_carmel_school.dto.parent_message_dto.ParentMessageDtoPost;
import com.example.mount_carmel_school.service.ParentMessageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/parent-messages")
@AllArgsConstructor
public class ParentMessageController {
    private final ParentMessageService parentMessageService;

    @GetMapping
    public ResponseEntity<List<ParentMessageDtoGet>> getAll() {
        return new  ResponseEntity<>(parentMessageService.getAll(), HttpStatus.OK);
    }


    @GetMapping("/paginated")
    public ResponseEntity<?> getAll(Pageable pageable) {
        return new  ResponseEntity<>(parentMessageService.getAll(pageable), HttpStatus.OK);
    }


    @GetMapping(path = "{id}")
    public ResponseEntity<ParentMessageDtoGet> get(
            @PathVariable("id") Long id) {
        return   new ResponseEntity<ParentMessageDtoGet>(parentMessageService.get(id),HttpStatus.OK);
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<ParentMessageDtoGet> update(
            @PathVariable("id") Long id,@RequestBody ParentMessageDtoPost parentMessageDtoPost) {
        return   new ResponseEntity<>(parentMessageService.update(id,parentMessageDtoPost),HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<DeleteResponseDto> delete(
            @PathVariable("id") Long id) {
        return   new ResponseEntity<DeleteResponseDto>(parentMessageService.delete(id),HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ParentMessageDtoGet> add(@RequestBody ParentMessageDtoPost parentMessageDtoPost)  {
        return new ResponseEntity<ParentMessageDtoGet>(parentMessageService.add(parentMessageDtoPost),HttpStatus.CREATED);
    }

    @GetMapping(path="/user/{userId}")
    public ResponseEntity<List<ParentMessageDtoGet>> getByUser(@PathVariable("userId") Long id) {
        return new  ResponseEntity<>(parentMessageService.getAllByUser(id), HttpStatus.OK);
    }

    @GetMapping(path="/user/{userId}/paginated")
    public ResponseEntity<?> getByUser(@PathVariable("userId") Long id,Pageable pageable) {
        return new  ResponseEntity<>(parentMessageService.getAllByUser(id,pageable), HttpStatus.OK);
    }


}
