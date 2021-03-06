package com.example.mount_carmel_school.service;


import com.example.mount_carmel_school.dto.DeleteResponseDto;
import com.example.mount_carmel_school.dto.PaginatedResponseDto;
import com.example.mount_carmel_school.dto.parent_message_dto.ParentMessageDtoGet;
import com.example.mount_carmel_school.dto.parent_message_dto.ParentMessageDtoPost;
import com.example.mount_carmel_school.exception.ApiRequestException;
import com.example.mount_carmel_school.exception.NotFoundException;
import com.example.mount_carmel_school.model.ParentMessage;
import com.example.mount_carmel_school.model.ParentMessageReceiver;
import com.example.mount_carmel_school.model.User;
import com.example.mount_carmel_school.repository.ParentMessageReceiverRepository;
import com.example.mount_carmel_school.repository.ParentMessageRepository;
import com.example.mount_carmel_school.repository.UserRepository;
//import com.example.mount_carmel_school.service.notification.processor.NewParentMessageNotificationProcessor;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.mount_carmel_school.enums.MessageDirection.FORWARD;
import static com.example.mount_carmel_school.enums.MessageDirection.REVERSE;
import static com.example.mount_carmel_school.enums.MessageStatus.ALL;
import static com.example.mount_carmel_school.enums.MessageStatus.PARTICULAR;
import static com.example.mount_carmel_school.enums.UserCategory.PARENT;
import static com.example.mount_carmel_school.enums.UserCategory.SCHOOL_EMPLOYEE;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class ParentMessageService {
    @Autowired
    private ParentMessageRepository parentMessageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParentMessageReceiverRepository parentMessageReceiverRepository;

//    @Autowired
//    private NewParentMessageNotificationProcessor processor;

    public ParentMessageDtoGet add(ParentMessageDtoPost parentMessageDtoPost) {

        User user = userRepository.findById(parentMessageDtoPost.getUser_senderId()).orElseThrow(() -> new NotFoundException("User Sender"));

        if (parentMessageDtoPost.getMessageStatus() == PARTICULAR) {
            User receiver = userRepository.findById(parentMessageDtoPost.getUser_receiverId()).orElseThrow(() -> new NotFoundException("User Receiver"));
            if (parentMessageDtoPost.getUser_senderId().equals(parentMessageDtoPost.getUser_receiverId())) {
                throw new ApiRequestException("Sender and Receiver are the same");
            }
        } else if (parentMessageDtoPost.getMessageStatus() == ALL && user.getCategory() == PARENT) {
            throw new ApiRequestException("A Parent is not allowed to send the Message with ALL status");
        }

        ParentMessage parentMessage = new ParentMessage();
        BeanUtils.copyProperties(parentMessageDtoPost, parentMessage);
        parentMessage.setSender(user);
        ParentMessage newParentMessage = parentMessageRepository.save(parentMessage);
        handleSendMessage(parentMessageDtoPost, newParentMessage);
        ParentMessageDtoGet saved = new ParentMessageDtoGet(newParentMessage);
//        processor.process(saved);
        return saved;
    }

    public void handleSendMessage(ParentMessageDtoPost parentMessageDtoPost, ParentMessage newParentMessage) {

        if (parentMessageDtoPost.getMessageStatus() == PARTICULAR) {
            User user = userRepository.findById(parentMessageDtoPost.getUser_receiverId()).orElseThrow(() -> new NotFoundException("User Receiver"));
            saveReceiver(user, newParentMessage);
        } else if (parentMessageDtoPost.getMessageStatus() == ALL) {
            List<User> receiversList = new ArrayList<>();

            if (parentMessageDtoPost.getMessageDirection() == FORWARD) {
                receiversList = userRepository.findAllByCategory(PARENT);
            } else if (parentMessageDtoPost.getMessageDirection() == REVERSE) {
                receiversList = userRepository.findAllByCategory(SCHOOL_EMPLOYEE);
            } else {
                throw new NotFoundException("MessageDirection");
            }

            for (User user : receiversList) {
                saveReceiver(user, newParentMessage);
            }
        } else {
            throw new NotFoundException("MessageStatus");
        }

    }

    public void saveReceiver(User user, ParentMessage newParentMessage) {
        ParentMessageReceiver parentMessageReceiver = new ParentMessageReceiver();
        parentMessageReceiver.setParentMessage(newParentMessage);
        parentMessageReceiver.setReceiver(user);
        parentMessageReceiverRepository.save(parentMessageReceiver);
    }


    public List<ParentMessageDtoGet> getAll() {
        List<ParentMessage> parentMessages = parentMessageRepository.findAll();
        List<ParentMessageDtoGet> formattedParentMessages = new ArrayList<>();
        return traverseCopy(parentMessages);
    }

    public PaginatedResponseDto getAll(Pageable pageable) {
        Page<ParentMessage> parentMessages = parentMessageRepository.findAll(pageable);
        return new PaginatedResponseDto(traverseCopy(parentMessages.getContent()), pageable.getPageNumber(), parentMessages.getTotalElements(), parentMessages.getTotalPages());
    }

    public ParentMessageDtoGet get(Long messageId) {
        ParentMessage parentMessage = parentMessageRepository.findById(messageId).orElseThrow(() -> new NotFoundException("Message"));
        return new ParentMessageDtoGet(parentMessage);
    }


    public ParentMessageDtoGet update(Long parentMessageId, ParentMessageDtoPost parentMessageDtoPost) {
        User user = userRepository.findById(parentMessageDtoPost.getUser_senderId()).orElseThrow(() -> new NotFoundException("User Sender"));
        ParentMessage parentMessage = parentMessageRepository.findById(parentMessageId).orElseThrow(() -> new NotFoundException("Parent Message"));

        if (parentMessage.getMessageDirection() != parentMessageDtoPost.getMessageDirection()) {
            throw new ApiRequestException("Direction of the Message should not be updated , Delete it instead");
        } else if (parentMessage.getMessageType() != parentMessageDtoPost.getMessageType()) {
            throw new ApiRequestException("Message Type can not be updated , Delete it instead");
        } else if (parentMessage.getMessageStatus() != parentMessageDtoPost.getMessageStatus()) {
            if (parentMessageDtoPost.getMessageStatus() == PARTICULAR) {
                throw new ApiRequestException("Message Status can not be changed from ALL TO PARTICULAR , Delete it instead");
            } else {
                handleSendMessage(parentMessageDtoPost, parentMessage);
            }
        }

        BeanUtils.copyProperties(parentMessageDtoPost, parentMessage);
        parentMessage.setSender(user);
        ParentMessage savedParent = parentMessageRepository.save(parentMessage);
        return new ParentMessageDtoGet(savedParent);
    }

    public DeleteResponseDto delete(Long parentMessageId) {
        ParentMessage parentMessage = parentMessageRepository.findById(parentMessageId).orElseThrow(() -> new NotFoundException("Parent Message"));
        parentMessageRepository.delete(parentMessage);
        return new DeleteResponseDto(parentMessage);
    }


    public List<ParentMessageDtoGet> getAllByUser(Long userSenderId) {

        User user = userRepository.findById(userSenderId).orElseThrow(() -> new NotFoundException("User Sender"));
        List<ParentMessage> parentMessages = parentMessageRepository.findAllBySender(user);
        List<ParentMessageDtoGet> parentMessageDtoGetList = new ArrayList<>();
        return traverseCopy(parentMessages);
    }


    public PaginatedResponseDto getAllByUser(Long userSenderId, Pageable pageable) {
        User user = userRepository.findById(userSenderId).orElseThrow(() -> new NotFoundException("User Sender"));
        Page<ParentMessage> parentMessages = parentMessageRepository.findAllBySender(user, pageable);
        return new PaginatedResponseDto(traverseCopy(parentMessages.getContent()), pageable.getPageNumber(), parentMessages.getTotalElements(), parentMessages.getTotalPages());
    }


    public List<ParentMessageDtoGet> traverseCopy(List<ParentMessage> list1) {
        List<ParentMessageDtoGet> list2 = new ArrayList<>();
        for (ParentMessage item : list1) {
            list2.add(new ParentMessageDtoGet(item));
        }
        return list2;
    }

}
