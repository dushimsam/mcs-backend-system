package com.example.mount_carmel_school.service;

import com.example.mount_carmel_school.dto.PaginatedResponseDto;
import com.example.mount_carmel_school.dto.contact_us_message_dto.ContactUsMessageDtoGet;
import com.example.mount_carmel_school.dto.contact_us_message_dto.ContactUsMessageDtoPost;
import com.example.mount_carmel_school.exception.ApiRequestException;
import com.example.mount_carmel_school.exception.NotFoundException;
import com.example.mount_carmel_school.model.ContactUsMessage;
import com.example.mount_carmel_school.repository.ContactUsMessageRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return new ContactUsMessageDtoGet(contactUsMessageRepository.save(contactUsMessage));
    }


    public List<ContactUsMessageDtoGet> getAll()
    {
      List<ContactUsMessage> contactUsMessages = contactUsMessageRepository.findAll();
       return traverseCopy(contactUsMessages);
    }

    public PaginatedResponseDto getPaginatedAll(Pageable pageable)
    {
        Page<ContactUsMessage>  messages = contactUsMessageRepository.findAll(pageable);
        return new PaginatedResponseDto(traverseCopy(messages.getContent()), pageable.getPageNumber(), messages.getTotalElements(), messages.getTotalPages());
    }


    public PaginatedResponseDto search(String key, Pageable pageable)
    {
      Page<ContactUsMessage>  messages = contactUsMessageRepository.findByEmailContainingAndNamesContainingAndMessageContaining(key,key,key,pageable);
      return new PaginatedResponseDto(traverseCopy(messages.getContent()), pageable.getPageNumber(), messages.getTotalElements(), messages.getTotalPages());
    }


    public ContactUsMessageDtoGet get(Long messageId)
    {
        return new ContactUsMessageDtoGet(contactUsMessageRepository.findById(messageId).orElseThrow(()->new NotFoundException("ContactUsMessage")));
    }


    public List<ContactUsMessageDtoGet> getByReadStatus(boolean status)
    {
        List<ContactUsMessage> contactUsMessages = contactUsMessageRepository.findContactUsMessageByIsRead(status);
        return traverseCopy(contactUsMessages);
    }

    public PaginatedResponseDto getByReadStatusPaginated(boolean status,Pageable pageable)
    {
        Page<ContactUsMessage>  messages = contactUsMessageRepository.findContactUsMessageByIsRead(status,pageable);
        return new PaginatedResponseDto(traverseCopy(messages.getContent()), pageable.getPageNumber(), messages.getTotalElements(), messages.getTotalPages());
    }

    public List<ContactUsMessageDtoGet> getByRepliedStatus(boolean status)
    {
        List<ContactUsMessage> contactUsMessages = contactUsMessageRepository.findContactUsMessageByIsReplied(status);
        return traverseCopy(contactUsMessages);
    }


    public PaginatedResponseDto getByRepliedStatusPaginated(boolean status,Pageable pageable)
    {
        Page<ContactUsMessage>  messages = contactUsMessageRepository.findContactUsMessageByIsReplied(status,pageable);
        return new PaginatedResponseDto(traverseCopy(messages.getContent()), pageable.getPageNumber(), messages.getTotalElements(), messages.getTotalPages());
    }

    public ContactUsMessageDtoGet markAsRead(Long messageId)
    {
        ContactUsMessage contactUsMessage = contactUsMessageRepository.findById(messageId).orElseThrow(()->new NotFoundException("ContactUsMessage"));
        contactUsMessage.setRead(true);
       return new ContactUsMessageDtoGet(contactUsMessageRepository.save(contactUsMessage));
    }

    public List<ContactUsMessageDtoGet> traverseCopy(List<ContactUsMessage> contactUsMessages)
    {
        List<ContactUsMessageDtoGet> formattedContactUsMessages = new ArrayList<>();
        for(ContactUsMessage item:contactUsMessages)
        {
            formattedContactUsMessages.add(new ContactUsMessageDtoGet(item));
        }
        return formattedContactUsMessages;
    }

}
