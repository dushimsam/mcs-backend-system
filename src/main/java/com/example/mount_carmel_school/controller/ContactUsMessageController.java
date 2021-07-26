package com.example.mount_carmel_school.controller;

import com.example.mount_carmel_school.dto.contact_us_message_dto.ContactUsMessageDtoGet;
import com.example.mount_carmel_school.dto.contact_us_message_dto.ContactUsMessageDtoPost;
import com.example.mount_carmel_school.dto.parent_dto.ParentDtoGet;
import com.example.mount_carmel_school.dto.parent_dto.ParentDtoPost;
import com.example.mount_carmel_school.service.ContactUsMessageService;
import com.example.mount_carmel_school.service.ParentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/contact-us-messages")
@AllArgsConstructor
public class ContactUsMessageController {
    private final ContactUsMessageService contactUsMessageService;

    @GetMapping
    public ResponseEntity<List<ContactUsMessageDtoGet>> getAll() {
        return new  ResponseEntity<List<ContactUsMessageDtoGet>>(contactUsMessageService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/unread")
    public ResponseEntity<List<ContactUsMessageDtoGet>>  getUnRead() {
        return new ResponseEntity<List<ContactUsMessageDtoGet>>(contactUsMessageService.getUnReadMessages(),HttpStatus.OK);
    }


    @GetMapping(path = "{id}")
    public ResponseEntity<ContactUsMessageDtoGet> get(
            @PathVariable("id") Long id) {
        return   new ResponseEntity<ContactUsMessageDtoGet>(contactUsMessageService.get(id),HttpStatus.OK);
    }

    @GetMapping(path = "/un-replied")
    public ResponseEntity<List<ContactUsMessageDtoGet>>  getUnRepliedMessages() {
        return   new ResponseEntity<List<ContactUsMessageDtoGet>>(contactUsMessageService.getUnRepliedMessages(),HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ContactUsMessageDtoGet> add(@RequestBody ContactUsMessageDtoPost contactUsMessageDtoPost)  {
        return new ResponseEntity<ContactUsMessageDtoGet>(contactUsMessageService.add(contactUsMessageDtoPost),HttpStatus.CREATED);
    }


    @PutMapping(path = "/mark-as-read/{id}")
    public ResponseEntity<ContactUsMessageDtoGet>  markAsRead(
            @PathVariable("id") Long id) {
        return   new ResponseEntity<ContactUsMessageDtoGet>(contactUsMessageService.markAsRead(id),HttpStatus.OK);
    }

}
