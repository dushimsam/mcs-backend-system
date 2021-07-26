package com.example.mount_carmel_school.controller;

import com.example.mount_carmel_school.dto.contact_us_message_dto.ContactUsMessageDtoGet;
import com.example.mount_carmel_school.dto.contact_us_message_dto.ContactUsMessageDtoPost;
import com.example.mount_carmel_school.dto.contact_us_message_reply_dto.ContactUsMessageReplyDtoGet;
import com.example.mount_carmel_school.dto.contact_us_message_reply_dto.ContactUsMessageReplyDtoPost;
import com.example.mount_carmel_school.model.ContactUsMessageReply;
import com.example.mount_carmel_school.service.ContactUsMessageReplyService;
import com.example.mount_carmel_school.service.ContactUsMessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/contact-us-message-replies")
@AllArgsConstructor
public class ContactUsMessageReplyController {
    private final ContactUsMessageReplyService contactUsMessageReplyService;

    @GetMapping
    public ResponseEntity<List<ContactUsMessageReplyDtoGet>> getAll() {
        return new ResponseEntity<List<ContactUsMessageReplyDtoGet>>(contactUsMessageReplyService.getAll(), HttpStatus.OK);
    }


    @GetMapping(path = "{id}")
    public ResponseEntity<ContactUsMessageReplyDtoGet> get(
            @PathVariable("id") Long id) {
        return   new ResponseEntity<ContactUsMessageReplyDtoGet>(contactUsMessageReplyService.get(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ContactUsMessageReplyDtoGet> add(@RequestBody ContactUsMessageReplyDtoPost contactUsMessageReplyDtoPost)  {
        return new ResponseEntity<ContactUsMessageReplyDtoGet>(contactUsMessageReplyService.add(contactUsMessageReplyDtoPost),HttpStatus.CREATED);
    }


    @GetMapping (path = "/massage/{messageId}")
    public ResponseEntity<List<ContactUsMessageReplyDtoGet>> getByMessage(
            @PathVariable("messageId") Long messageId) {
        return  new  ResponseEntity<List<ContactUsMessageReplyDtoGet>>(contactUsMessageReplyService.getByMessage(messageId),HttpStatus.OK);
    }


    @PutMapping(path = "{id}")
    public ResponseEntity<ContactUsMessageReplyDtoGet> update(
            @PathVariable("id") Long id,@RequestBody ContactUsMessageReplyDtoPost contactUsMessageReplyDtoPost) {
        return  new ResponseEntity<ContactUsMessageReplyDtoGet>(contactUsMessageReplyService.update(id,contactUsMessageReplyDtoPost),HttpStatus.OK);
    }
}
