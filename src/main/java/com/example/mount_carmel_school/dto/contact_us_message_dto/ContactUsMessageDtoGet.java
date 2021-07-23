package com.example.mount_carmel_school.dto.contact_us_message_dto;

import com.example.mount_carmel_school.dto.contact_us_message_reply_dto.ContactUsMessageReplyDtoGet;
import com.example.mount_carmel_school.model.ContactUsMessage;
import com.example.mount_carmel_school.model.ContactUsMessageReply;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class ContactUsMessageDtoGet {
    private Long id;
    private String names;
    private String email;
    private String message;
    private List<ContactUsMessageReplyDtoGet> contactUsMessageReplyDtoGets;
    public ContactUsMessageDtoGet(ContactUsMessage contactUsMessage)
    {
        BeanUtils.copyProperties(contactUsMessage,this,"contactUsMessageReplyDtoGets");

        for(ContactUsMessageReply item:contactUsMessage.getContactUsMessageReplies())
        {
            this.contactUsMessageReplyDtoGets.add(new ContactUsMessageReplyDtoGet(item));
        }
    }
}
