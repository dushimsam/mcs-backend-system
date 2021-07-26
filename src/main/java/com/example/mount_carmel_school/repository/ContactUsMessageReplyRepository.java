package com.example.mount_carmel_school.repository;

import com.example.mount_carmel_school.model.ContactUsMessage;
import com.example.mount_carmel_school.model.ContactUsMessageReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactUsMessageReplyRepository extends JpaRepository<ContactUsMessageReply,Long> {
    public List<ContactUsMessageReply> findContactUsMessageReplyByContactUsMessage(ContactUsMessage contactUsMessage);

}
