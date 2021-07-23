package com.example.mount_carmel_school.controller;

import com.example.mount_carmel_school.dto.contact_us_message_dto.ContactUsMessageDtoGet;
import com.example.mount_carmel_school.dto.contact_us_message_dto.ContactUsMessageDtoPost;
import com.example.mount_carmel_school.dto.parent_dto.ParentDtoGet;
import com.example.mount_carmel_school.dto.parent_dto.ParentDtoPost;
import com.example.mount_carmel_school.service.ContactUsMessageService;
import com.example.mount_carmel_school.service.ParentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/contact-us-messages")
@AllArgsConstructor
public class ContactUsMessageController {
    private final ContactUsMessageService contactUsMessageService;

    @GetMapping
    public List<ContactUsMessageDtoGet>  getAll() {
        return contactUsMessageService.getAll();
    }

    @GetMapping
    public List<ContactUsMessageDtoGet>  getUnRead() {
        return contactUsMessageService.getUnReadMessages();
    }


    @GetMapping(path = "{id}")
    public ContactUsMessageDtoGet get(
            @PathVariable("id") Long id) {
        return   contactUsMessageService.get(id);
    }

    @GetMapping(path = "/un-replied")
    public List<ContactUsMessageDtoGet>  getUnRepliedMessages() {
        return   contactUsMessageService.getUnRepliedMessages();
    }


    @PostMapping
    public ContactUsMessageDtoGet add(@RequestBody ContactUsMessageDtoPost contactUsMessageDtoPost)  {
        return contactUsMessageService.add(contactUsMessageDtoPost);
    }


    @PutMapping(path = "/mark-as-read/{id}")
    public ContactUsMessageDtoGet markAsRead(
            @PathVariable("id") Long id) {
        return   contactUsMessageService.markAsRead(id);
    }

}
