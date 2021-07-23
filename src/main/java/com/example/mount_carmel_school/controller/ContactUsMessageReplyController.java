package com.example.mount_carmel_school.controller;

import com.example.mount_carmel_school.dto.contact_us_message_dto.ContactUsMessageDtoGet;
import com.example.mount_carmel_school.dto.contact_us_message_dto.ContactUsMessageDtoPost;
import com.example.mount_carmel_school.dto.contact_us_message_reply_dto.ContactUsMessageReplyDtoGet;
import com.example.mount_carmel_school.dto.contact_us_message_reply_dto.ContactUsMessageReplyDtoPost;
import com.example.mount_carmel_school.model.ContactUsMessageReply;
import com.example.mount_carmel_school.service.ContactUsMessageReplyService;
import com.example.mount_carmel_school.service.ContactUsMessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/contact-us-message-replies")
@AllArgsConstructor
public class ContactUsMessageReplyController {
    private final ContactUsMessageReplyService contactUsMessageReplyService;

    @GetMapping
    public List<ContactUsMessageReplyDtoGet> getAll() {
        return contactUsMessageReplyService.getAll();
    }


    @GetMapping(path = "{id}")
    public ContactUsMessageReplyDtoGet get(
            @PathVariable("id") Long id) {
        return   contactUsMessageReplyService.get(id);
    }

    @PostMapping
    public ContactUsMessageReplyDtoGet add(@RequestBody ContactUsMessageReplyDtoPost contactUsMessageReplyDtoPost)  {
        return contactUsMessageReplyService.add(contactUsMessageReplyDtoPost);
    }



    @GetMapping (path = "/massage/{messageId}")
    public List<ContactUsMessageReplyDtoGet> getByMessage(
            @PathVariable("messageId") Long messageId) {
        return   contactUsMessageReplyService.getByMessage(messageId);
    }


    @PutMapping(path = "{id}")
    public ContactUsMessageReplyDtoGet getByMessage(
            @PathVariable("id") Long id,@RequestBody ContactUsMessageReplyDtoPost contactUsMessageReplyDtoPost) {
        return   contactUsMessageReplyService.update(id,contactUsMessageReplyDtoPost);
    }
}
