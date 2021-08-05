package com.example.mount_carmel_school.repository;

import com.example.mount_carmel_school.model.ContactUsMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactUsMessageRepository extends JpaRepository<ContactUsMessage,Long> {
    public List<ContactUsMessage> findContactUsMessageByEmail(String email);
    public List<ContactUsMessage> findContactUsMessageByIsRead(boolean status);
    public Page<ContactUsMessage> findContactUsMessageByIsRead(boolean status,Pageable pageable);
    public List<ContactUsMessage> findContactUsMessageByIsReplied(boolean status);
    public Page<ContactUsMessage> findContactUsMessageByIsReplied(boolean reply_status,Pageable pageable);
    public ContactUsMessage findContactUsMessageByEmailAndMessageAndNames(String email,String message,String names);
    public Page<ContactUsMessage>  findByEmailContainingAndNamesContainingAndMessageContaining(String email, String name, String message, Pageable pageable);
}
