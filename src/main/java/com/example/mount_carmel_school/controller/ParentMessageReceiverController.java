package com.example.mount_carmel_school.controller;


import com.example.mount_carmel_school.dto.parent_message_receiver_dto.ParentMessageReceiverDtoGet;
import com.example.mount_carmel_school.dto.parent_message_receiver_dto.ParentMessageReceiverDtoPost;
import com.example.mount_carmel_school.service.ParentMessageReceiverService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/parent-message-receivers")
@AllArgsConstructor
public class ParentMessageReceiverController {
    private final ParentMessageReceiverService parentMessageReceiverService;

    @GetMapping
    public ResponseEntity<List<ParentMessageReceiverDtoGet>> getAll() {
        return new  ResponseEntity<>(parentMessageReceiverService.getAll(), HttpStatus.OK);
    }


    @GetMapping(path = "/parent-message/{parentMessageId}")
    public ResponseEntity<List<ParentMessageReceiverDtoGet>> getAllByParentMessage(@PathVariable("parentMessageId") Long parentMessageId) {
        return new  ResponseEntity<>(parentMessageReceiverService.getAllByParentMessage(parentMessageId), HttpStatus.OK);
    }


    @GetMapping(path="/read/{status}")
    public ResponseEntity<List<ParentMessageReceiverDtoGet>> getAllByReadStatus(@PathVariable("status") boolean status) {
        return new  ResponseEntity<>(parentMessageReceiverService.getAllByReadStatus(status), HttpStatus.OK);
    }


    @GetMapping(path="/read/{status}/user/{userId}")
    public ResponseEntity<List<ParentMessageReceiverDtoGet>> getAllByReadStatusUser(@PathVariable("status") boolean status,@PathVariable("userId") Long userId) {
        return new  ResponseEntity<>(parentMessageReceiverService.getAllByReadStatusUser(status,userId), HttpStatus.OK);
    }

    @GetMapping(path="/user/{userId}")
    public ResponseEntity<List<ParentMessageReceiverDtoGet>> getAllByUser(@PathVariable("userId") Long userId) {
        return new  ResponseEntity<>(parentMessageReceiverService.getAllByUser(userId), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ParentMessageReceiverDtoGet> get(
            @PathVariable("id") Long id) {
        return   new ResponseEntity<ParentMessageReceiverDtoGet>(parentMessageReceiverService.get(id),HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ParentMessageReceiverDtoGet> add(@RequestBody ParentMessageReceiverDtoPost parentMessageDtoPost)  {
        return new ResponseEntity<ParentMessageReceiverDtoGet>(parentMessageReceiverService.add(parentMessageDtoPost),HttpStatus.CREATED);
    }

//    @GetMapping(path="/user/{userId}")
//    public ResponseEntity<List<ParentMessageReceiverDtoGet>> getByUser(@PathVariable("userId") Long id) {
//        return new  ResponseEntity<>(parentMessageReceiverService.getAllByUser(id), HttpStatus.OK);
//    }

}
