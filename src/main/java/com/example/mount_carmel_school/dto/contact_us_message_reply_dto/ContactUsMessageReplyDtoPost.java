package com.example.mount_carmel_school.dto.contact_us_message_reply_dto;

import lombok.Data;

@Data
public class ContactUsMessageReplyDtoPost {
    private Long schoolEmployeeId;
    private String replyMessage;
    private Long contactUsMessageId;
    private String emailUsedToReply;
}
