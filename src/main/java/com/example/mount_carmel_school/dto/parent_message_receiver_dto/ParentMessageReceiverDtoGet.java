package com.example.mount_carmel_school.dto.parent_message_receiver_dto;

import com.example.mount_carmel_school.dto.UserDto.UserDtoGet;
import com.example.mount_carmel_school.dto.parent_message_dto.ParentMessageDtoGet;
import com.example.mount_carmel_school.model.ParentMessage;
import com.example.mount_carmel_school.model.ParentMessageReceiver;
import com.example.mount_carmel_school.model.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class ParentMessageReceiverDtoGet {

    private Long id;
    private Boolean isRead;
    private ParentMessageDtoGet parentMessageDtoGet;
    private UserDtoGet receiver;

    public ParentMessageReceiverDtoGet(ParentMessageReceiver parentMessageReceiver)
    {
        BeanUtils.copyProperties(parentMessageReceiver,this,"receiver","parentMessageDtoGet");
        this.receiver = new UserDtoGet(parentMessageReceiver.getReceiver());
        this.parentMessageDtoGet = new ParentMessageDtoGet(parentMessageReceiver.getParentMessage());
    }
}
