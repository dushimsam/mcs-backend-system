package com.example.mount_carmel_school.dto.parent_message_dto;

import com.example.mount_carmel_school.dto.UserDto.UserDtoGet;
import com.example.mount_carmel_school.enums.MessageDirection;
import com.example.mount_carmel_school.enums.MessageStatus;
import com.example.mount_carmel_school.enums.MessageType;
import com.example.mount_carmel_school.model.ParentMessage;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class ParentMessageDtoGet {
    private Long id;
    private MessageType messageType;
    private String message;
    private MessageDirection messageDirection;
    private MessageStatus messageStatus;
    private UserDtoGet sender;
    private Date createdAt;
    private Date lastModifiedAt;
    public ParentMessageDtoGet(ParentMessage parentMessage)
    {
        BeanUtils.copyProperties(parentMessage,this,"sender");
        this.sender = new UserDtoGet(parentMessage.getSender());
    }
}
