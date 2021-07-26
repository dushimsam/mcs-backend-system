package com.example.mount_carmel_school.dto.parent_message_dto;

import com.example.mount_carmel_school.dto.UserDto.UserDtoGet;
import com.example.mount_carmel_school.enums.MessageDirection;
import com.example.mount_carmel_school.enums.MessageStatus;
import com.example.mount_carmel_school.enums.MessageType;
import com.example.mount_carmel_school.model.ParentMessage;
import lombok.Data;

import java.util.Date;

@Data
public class ParentMessageDtoGet {
    private Long id;
    private MessageType messageType;
    private String message;
    private MessageDirection messageDirection;
    private MessageStatus messageStatus;
    private UserDtoGet user;
    private Date createdAt;
    private Date lastModifiedAt;
    public ParentMessageDtoGet(ParentMessage parentMessage)
    {
        this.user = new UserDtoGet(parentMessage.getSender());
    }
}
