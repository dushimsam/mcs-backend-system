package com.example.mount_carmel_school.dto.contact_us_message_dto;

import com.example.mount_carmel_school.dto.contact_us_message_reply_dto.ContactUsMessageReplyDtoGet;
import com.example.mount_carmel_school.model.ContactUsMessage;
import com.example.mount_carmel_school.model.ContactUsMessageReply;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ContactUsMessageDtoGet {
    private Long id;
    private String names;
    private String email;
    private String message;
    private boolean isRead;
    private boolean isReplied;
    private List<ContactUsMessageReplyDtoGet> contactUsMessageReplyDtoGets = new ArrayList<>();
    private Date createdAt;
    private Date lastModifiedAt;
    public ContactUsMessageDtoGet(ContactUsMessage contactUsMessage)
    {
        BeanUtils.copyProperties(contactUsMessage,this,"contactUsMessageReplyDtoGets");

        if(contactUsMessage.getContactUsMessageReplies() != null)
        {
            for(ContactUsMessageReply item:contactUsMessage.getContactUsMessageReplies())
            {
                this.contactUsMessageReplyDtoGets.add(new ContactUsMessageReplyDtoGet(item,"MESSAGE"));
            }
        }

    }

    public ContactUsMessageDtoGet(ContactUsMessage contactUsMessage,String ignore)
    {
        BeanUtils.copyProperties(contactUsMessage,this,"contactUsMessageReplyDtoGets");

        if(!ignore.equals("REPLY") && contactUsMessage.getContactUsMessageReplies() != null)
        {
            for(ContactUsMessageReply item:contactUsMessage.getContactUsMessageReplies())
            {
                this.contactUsMessageReplyDtoGets.add(new ContactUsMessageReplyDtoGet(item,"MESSAGE"));
            }
        }

    }
}
