package com.example.mount_carmel_school.service;

import com.example.mount_carmel_school.dto.contact_us_message_reply_dto.ContactUsMessageReplyDtoGet;
import com.example.mount_carmel_school.dto.contact_us_message_reply_dto.ContactUsMessageReplyDtoPost;
import com.example.mount_carmel_school.enums.UserCategory;
import com.example.mount_carmel_school.exception.ApiRequestException;
import com.example.mount_carmel_school.exception.NotFoundException;
import com.example.mount_carmel_school.model.ContactUsMessage;
import com.example.mount_carmel_school.model.ContactUsMessageReply;
import com.example.mount_carmel_school.model.SchoolEmployee;
import com.example.mount_carmel_school.repository.ContactUsMessageReplyRepository;
import com.example.mount_carmel_school.repository.ContactUsMessageRepository;
import com.example.mount_carmel_school.repository.SchoolEmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class ContactUsMessageReplyService {
    @Autowired
    private ContactUsMessageReplyRepository contactUsMessageReplyRepository;

    @Autowired
    private ContactUsMessageRepository contactUsMessageRepository;

    @Autowired
    private SchoolEmployeeRepository schoolEmployeeRepository;

    public ContactUsMessageReplyDtoGet add(ContactUsMessageReplyDtoPost contactUsMessageReplyDtoPost)  {
        ContactUsMessageReply newReply = new ContactUsMessageReply();
        SchoolEmployee schoolEmployee = schoolEmployeeRepository.findById(contactUsMessageReplyDtoPost.getSchoolEmployeeId()).orElseThrow(()->new NotFoundException("School Employee"));

        if(schoolEmployee.getUser().getCategory() != UserCategory.SCHOOL_EMPLOYEE)
        {
            throw  new ApiRequestException("The user replier should be the school-employee");
        }


        ContactUsMessage contactUsMessage = contactUsMessageRepository.findById(contactUsMessageReplyDtoPost.getContactUsMessageId()).orElseThrow(()->new NotFoundException("Contact us Message"));
        newReply.setSchoolEmployee(schoolEmployee);
        newReply.setContactUsMessage(contactUsMessage);
        newReply.setReplyEmail(contactUsMessageReplyDtoPost.getEmailUsedToReply());
        newReply.setReplyMessage(contactUsMessageReplyDtoPost.getReplyMessage());
        contactUsMessage.setRead(true);
        contactUsMessage.setReplied(true);
        contactUsMessageRepository.save(contactUsMessage);
        return new ContactUsMessageReplyDtoGet(contactUsMessageReplyRepository.save(newReply));
    }

    public ContactUsMessageReplyDtoGet get(Long replyId)
    {
        return new ContactUsMessageReplyDtoGet(contactUsMessageReplyRepository.findById(replyId).orElseThrow(()->new NotFoundException("Contact us Message")));
    }

    public List<ContactUsMessageReplyDtoGet> getAll()
    {
        List<ContactUsMessageReply> contactUsMessages = contactUsMessageReplyRepository.findAll();
        List<ContactUsMessageReplyDtoGet> formattedContactUsReplies = new ArrayList<>();

        for(ContactUsMessageReply item:contactUsMessages)
        {
            formattedContactUsReplies.add(new ContactUsMessageReplyDtoGet(item));
        }
        return formattedContactUsReplies;
    }

    public List<ContactUsMessageReplyDtoGet> getByMessage(Long messageId)
    {
        ContactUsMessage contactUsMessage = contactUsMessageRepository.findById(messageId).get();
        List<ContactUsMessageReply> contactUsMessages = contactUsMessageReplyRepository.findContactUsMessageReplyByContactUsMessage(contactUsMessage);
        List<ContactUsMessageReplyDtoGet> formattedContactUsReplies = new ArrayList<>();
        for(ContactUsMessageReply item:contactUsMessages)
        {
            formattedContactUsReplies.add(new ContactUsMessageReplyDtoGet(item));
        }
        return formattedContactUsReplies;
    }

    public ContactUsMessageReplyDtoGet update(Long replyId,ContactUsMessageReplyDtoPost contactUsMessageReplyDtoPost)
    {
        ContactUsMessageReply contactUsMessageReply = contactUsMessageReplyRepository.findById(replyId).get();
        SchoolEmployee schoolEmployee = schoolEmployeeRepository.findById(contactUsMessageReplyDtoPost.getSchoolEmployeeId()).get();
        ContactUsMessage contactUsMessage = contactUsMessageRepository.findById(contactUsMessageReplyDtoPost.getContactUsMessageId()).get();
        contactUsMessageReply.setSchoolEmployee(schoolEmployee);
        contactUsMessageReply.setContactUsMessage(contactUsMessage);
        contactUsMessageReply.setReplyEmail(contactUsMessageReplyDtoPost.getEmailUsedToReply());
        contactUsMessageReply.setReplyMessage(contactUsMessageReplyDtoPost.getReplyMessage());
        return new ContactUsMessageReplyDtoGet(contactUsMessageReplyRepository.save(contactUsMessageReply));
    }

    public String delete(Long replyId)
    {
        ContactUsMessageReply contactUsMessageReply = contactUsMessageReplyRepository.findById(replyId).get();
        ContactUsMessage contactUsMessage = contactUsMessageReply.getContactUsMessage();
        contactUsMessage.setRead(false);
        contactUsMessageRepository.save(contactUsMessage);
        contactUsMessageReplyRepository.delete(contactUsMessageReply);
        return "DELETED SUCCESSFULLY";
    }
}
