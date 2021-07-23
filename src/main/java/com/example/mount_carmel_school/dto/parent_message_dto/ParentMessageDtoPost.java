package com.example.mount_carmel_school.dto.parent_message_dto;

import com.example.mount_carmel_school.enums.MessageDirection;
import com.example.mount_carmel_school.enums.MessageStatus;
import com.example.mount_carmel_school.enums.MessageType;
import lombok.Data;

@Data
public class ParentMessageDtoPost {
  private Long user_senderId;
  private Long user_receiverId;
  private MessageType messageType = MessageType.TEXT;
  private String message;
  private MessageDirection messageDirection;
  private MessageStatus messageStatus;
}
