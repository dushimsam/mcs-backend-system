package com.example.mount_carmel_school.dto.contact_us_message_reply_dto;

import com.example.mount_carmel_school.dto.contact_us_message_dto.ContactUsMessageDtoGet;
import com.example.mount_carmel_school.dto.school_employee_dto.SchoolEmployeeDtoGet;
import com.example.mount_carmel_school.model.ContactUsMessageReply;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class ContactUsMessageReplyDtoGet {
    private Long id;
    private SchoolEmployeeDtoGet schoolEmployeeDtoGet;
    private ContactUsMessageDtoGet contactUsMessageDtoGet;
    private String replyMessage;
    public ContactUsMessageReplyDtoGet(ContactUsMessageReply contactUsMessageReply)
    {
        BeanUtils.copyProperties(contactUsMessageReply,this,"schoolEmployeeDtoGet","contactUsMessageDtoGet");
        this.schoolEmployeeDtoGet = new SchoolEmployeeDtoGet(contactUsMessageReply.getSchoolEmployee());
        this.contactUsMessageDtoGet = new ContactUsMessageDtoGet(contactUsMessageReply.getContactUsMessage());
    }
}
