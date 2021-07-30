package com.example.mount_carmel_school.repository;

import com.example.mount_carmel_school.model.ContactUsMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactUsMessageRepository extends JpaRepository<ContactUsMessage,Long> {
    public List<ContactUsMessage> findContactUsMessageByEmail(String email);
    public List<ContactUsMessage> findContactUsMessageByIsRead(boolean status);
    public List<ContactUsMessage> findContactUsMessageByIsReplied(boolean status);
    public List<ContactUsMessage> findContactUsMessageByIsRepliedAndIsRead(boolean reply_status,boolean read_status);
    public ContactUsMessage findContactUsMessageByEmailAndMessageAndNames(String email,String message,String names);
}
