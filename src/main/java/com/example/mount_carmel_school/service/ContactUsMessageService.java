package com.example.mount_carmel_school.service;

import com.example.mount_carmel_school.dto.contact_us_message_dto.ContactUsMessageDtoGet;
import com.example.mount_carmel_school.dto.contact_us_message_dto.ContactUsMessageDtoPost;
import com.example.mount_carmel_school.exception.ApiRequestException;
import com.example.mount_carmel_school.model.ContactUsMessage;
import com.example.mount_carmel_school.repository.ContactUsMessageRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class ContactUsMessageService {
    @Autowired
    private ContactUsMessageRepository contactUsMessageRepository;

    public ContactUsMessageDtoGet add(ContactUsMessageDtoPost contactUsMessageDtoPost){
        ContactUsMessage contactUsMessage = new ContactUsMessage();
        BeanUtils.copyProperties(contactUsMessageDtoPost,contactUsMessage);
//        if(contactUsMessageRepository.findContactUsMessageByEmailAndMessageAndNames(contactUsMessageDtoPost.getEmail(),contactUsMessageDtoPost.getMessage(),contactUsMessageDtoPost.getNames()) != null)
//        {
//            throw new ApiRequestException("DUPLICATES NOT ALLOWED");
//        }
        return new ContactUsMessageDtoGet(contactUsMessageRepository.save(contactUsMessage));
    }


    public List<ContactUsMessageDtoGet> getAll()
    {
      List<ContactUsMessage> contactUsMessages = contactUsMessageRepository.findAll();
        List<ContactUsMessageDtoGet> formattedContactUsMessages = new ArrayList<>();

        for(ContactUsMessage item:contactUsMessages)
        {
            formattedContactUsMessages.add(new ContactUsMessageDtoGet(item));
        }
        return formattedContactUsMessages;
    }

    public ContactUsMessageDtoGet get(Long messageId)
    {
        return new ContactUsMessageDtoGet(contactUsMessageRepository.findById(messageId).get());
    }


    public List<ContactUsMessageDtoGet> getByReadStatus(boolean status)
    {
        List<ContactUsMessage> contactUsMessages = contactUsMessageRepository.findContactUsMessageByIsRead(status);
        List<ContactUsMessageDtoGet> formattedContactUsMessages = new ArrayList<>();

        for(ContactUsMessage item:contactUsMessages)
        {
            formattedContactUsMessages.add(new ContactUsMessageDtoGet(item));
        }
        return formattedContactUsMessages;
    }

    public List<ContactUsMessageDtoGet> getByRepliedStatus(boolean status)
    {
        List<ContactUsMessage> contactUsMessages = contactUsMessageRepository.findContactUsMessageByIsReplied(status);
        List<ContactUsMessageDtoGet> formattedContactUsMessages = new ArrayList<>();

        for(ContactUsMessage item:contactUsMessages)
        {
            formattedContactUsMessages.add(new ContactUsMessageDtoGet(item));
        }
        return formattedContactUsMessages;
    }


    public ContactUsMessageDtoGet markAsRead(Long messageId)
    {
        ContactUsMessage contactUsMessage = contactUsMessageRepository.findById(messageId).get();
        contactUsMessage.setRead(true);
       return new ContactUsMessageDtoGet(contactUsMessageRepository.save(contactUsMessage));
    }


}
