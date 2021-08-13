package com.example.mount_carmel_school.controller;


import com.example.mount_carmel_school.dto.parent_message_receiver_dto.ParentMessageReceiverDtoGet;
import com.example.mount_carmel_school.dto.parent_message_receiver_dto.ParentMessageReceiverDtoPost;
import com.example.mount_carmel_school.service.ParentMessageReceiverService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
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


    @GetMapping("/paginated")
    public ResponseEntity<?> getAll(Pageable pageable) {
        return new  ResponseEntity<>(parentMessageReceiverService.getAll(pageable), HttpStatus.OK);
    }


    @GetMapping(path = "/parent-message/{parentMessageId}")
    public ResponseEntity<List<ParentMessageReceiverDtoGet>> getAllByParentMessage(@PathVariable("parentMessageId") Long parentMessageId) {
        return new  ResponseEntity<>(parentMessageReceiverService.getAllByParentMessage(parentMessageId), HttpStatus.OK);
    }

    @GetMapping(path = "/parent-message/{parentMessageId}/paginated")
    public ResponseEntity<?> getAllByParentMessage(@PathVariable("parentMessageId") Long parentMessageId,Pageable pageable) {
        return new  ResponseEntity<>(parentMessageReceiverService.getAllByParentMessage(parentMessageId,pageable), HttpStatus.OK);
    }

    @GetMapping(path="/read/{status}")
    public ResponseEntity<List<ParentMessageReceiverDtoGet>> getAllByReadStatus(@PathVariable("status") boolean status) {
        return new  ResponseEntity<>(parentMessageReceiverService.getAllByReadStatus(status), HttpStatus.OK);
    }


    @GetMapping(path="/read/{status}/paginated")
    public ResponseEntity<?> getAllByReadStatus(@PathVariable("status") boolean status,Pageable pageable) {
        return new  ResponseEntity<>(parentMessageReceiverService.getAllByReadStatus(status,pageable), HttpStatus.OK);
    }


    @GetMapping(path="/read/status/{status}/user/{userId}")
    public ResponseEntity<List<ParentMessageReceiverDtoGet>> getAllByReadStatusUser(@PathVariable("status") boolean status,@PathVariable("userId") Long userId) {
        return new  ResponseEntity<>(parentMessageReceiverService.getAllByReadStatusUser(status,userId), HttpStatus.OK);
    }

    @GetMapping(path="/read/status/{status}/user/{userId}/paginated")
    public ResponseEntity<?> getAllByReadStatusUser(@PathVariable("status") boolean status,@PathVariable("userId") Long userId,Pageable pageable) {
        return new  ResponseEntity<>(parentMessageReceiverService.getAllByReadStatusUser(status,userId,pageable), HttpStatus.OK);
    }

    @GetMapping(path="/user/{userId}")
    public ResponseEntity<List<ParentMessageReceiverDtoGet>> getAllByUser(@PathVariable("userId") Long userId) {
        return new  ResponseEntity<>(parentMessageReceiverService.getAllByUser(userId), HttpStatus.OK);
    }



    @GetMapping(path="/user/{userId}/paginated")
    public ResponseEntity<?> getAllByUser(@PathVariable("userId") Long userId,Pageable pageable) {
        return new  ResponseEntity<>(parentMessageReceiverService.getAllByUser(userId,pageable), HttpStatus.OK);
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

    @PutMapping(path = "/mark-as-read/{id}")
    public ResponseEntity<ParentMessageReceiverDtoGet> markAsRead(
            @PathVariable("id") Long id) {
        return   new ResponseEntity<ParentMessageReceiverDtoGet>(parentMessageReceiverService.markAsRead(id),HttpStatus.OK);
    }

    @GetMapping(path="/user/chat/{userId}")
    public ResponseEntity<List<ParentMessageReceiverDtoGet>> getChatByUser(@PathVariable("userId") Long id) {
        return new  ResponseEntity<>(parentMessageReceiverService.getChatByUser(id), HttpStatus.OK);
    }

    @GetMapping(path="/user/chat/{userId}/paginated")
    public ResponseEntity<?> getChatByUser(@PathVariable("userId") Long id, Pageable pageable) {
        return new  ResponseEntity<>(parentMessageReceiverService.getChatByUser(id,pageable), HttpStatus.OK);
    }

}
