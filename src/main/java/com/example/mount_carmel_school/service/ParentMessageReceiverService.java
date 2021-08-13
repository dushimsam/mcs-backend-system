package com.example.mount_carmel_school.service;


import com.example.mount_carmel_school.dto.PaginatedResponseDto;
import com.example.mount_carmel_school.dto.parent_message_receiver_dto.ParentMessageReceiverDtoGet;
import com.example.mount_carmel_school.dto.parent_message_receiver_dto.ParentMessageReceiverDtoPost;
import com.example.mount_carmel_school.enums.MessageStatus;
import com.example.mount_carmel_school.exception.NotFoundException;
import com.example.mount_carmel_school.model.ParentMessage;
import com.example.mount_carmel_school.model.ParentMessageReceiver;
import com.example.mount_carmel_school.model.User;
import com.example.mount_carmel_school.repository.ParentMessageReceiverRepository;
import com.example.mount_carmel_school.repository.ParentMessageRepository;
import com.example.mount_carmel_school.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class ParentMessageReceiverService {
    @Autowired
    private ParentMessageReceiverRepository parentMessageReceiverRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParentMessageRepository messageRepository;

    public ParentMessageReceiverDtoGet add(ParentMessageReceiverDtoPost parentMessageReceiverDtoPost) {
        ParentMessageReceiver parentMessageReceiver = new ParentMessageReceiver();
        User receiver = userRepository.findById(parentMessageReceiverDtoPost.getReceiver_userId()).orElseThrow(() -> new NotFoundException("User"));
        ParentMessage parentMessage = messageRepository.findById(parentMessageReceiverDtoPost.getParentMessageId()).orElseThrow(() -> new NotFoundException("Parent Message"));
        parentMessageReceiver.setReceiver(receiver);
        parentMessageReceiver.setParentMessage(parentMessage);
        return new ParentMessageReceiverDtoGet(parentMessageReceiverRepository.save(parentMessageReceiver));
    }

    public List<ParentMessageReceiverDtoGet> getAll() {
        List<ParentMessageReceiver> parentMessageReceivers = parentMessageReceiverRepository.findAll();
        List<ParentMessageReceiverDtoGet> parentMessageReceiverDtoGetList = new ArrayList<>();
        return traverseCopy(parentMessageReceivers);
    }

    public PaginatedResponseDto getAll(Pageable pageable) {
        Page<ParentMessageReceiver> parentMessageReceivers = parentMessageReceiverRepository.findAll(pageable);
        return new PaginatedResponseDto(traverseCopy(parentMessageReceivers.getContent()), pageable.getPageNumber(), parentMessageReceivers.getTotalElements(), parentMessageReceivers.getTotalPages());
    }


    public List<ParentMessageReceiverDtoGet> getAllByParentMessage(Long parentMessageId) {
        ParentMessage parentMessage = messageRepository.findById(parentMessageId).orElseThrow(() -> new NotFoundException("ParentMessage"));
        List<ParentMessageReceiver> parentMessageReceivers = parentMessageReceiverRepository.findAllByParentMessage(parentMessage);
        List<ParentMessageReceiverDtoGet> parentMessageReceiverDtoGetList = new ArrayList<>();
        return traverseCopy(parentMessageReceivers);
    }

    public PaginatedResponseDto getAllByParentMessage(Long parentMessageId,Pageable pageable) {
        ParentMessage parentMessage = messageRepository.findById(parentMessageId).orElseThrow(() -> new NotFoundException("ParentMessage"));
        Page<ParentMessageReceiver> parentMessageReceivers = parentMessageReceiverRepository.findAllByParentMessage(parentMessage,pageable);
        return new PaginatedResponseDto(traverseCopy(parentMessageReceivers.getContent()), pageable.getPageNumber(), parentMessageReceivers.getTotalElements(), parentMessageReceivers.getTotalPages());
    }


    public ParentMessageReceiverDtoGet get(Long receiverId) {
        ParentMessageReceiver parentMessageReceiver = parentMessageReceiverRepository.findById(receiverId).orElseThrow(() -> new NotFoundException("Parent Message Receiver"));
        return new ParentMessageReceiverDtoGet(parentMessageReceiver);
    }


    public ParentMessageReceiverDtoGet markAsRead(Long receiverId) {
        ParentMessageReceiver parentMessageReceiver = parentMessageReceiverRepository.findById(receiverId).orElseThrow(() -> new NotFoundException("ParentMessageReceiver"));
        parentMessageReceiver.setIsRead(true);
        return new ParentMessageReceiverDtoGet(parentMessageReceiverRepository.save(parentMessageReceiver));
    }

    public List<ParentMessageReceiverDtoGet> getAllByReadStatus(boolean isRead) {
        List<ParentMessageReceiver> parentMessageReceivers = parentMessageReceiverRepository.findByIsRead(isRead);
        List<ParentMessageReceiverDtoGet> parentMessageReceiverDtoGetList = new ArrayList<>();
        return traverseCopy(parentMessageReceivers);
    }

    public PaginatedResponseDto getAllByReadStatus(boolean isRead,Pageable pageable) {
        Page<ParentMessageReceiver> parentMessageReceivers = parentMessageReceiverRepository.findByIsRead(isRead,pageable);
        return new PaginatedResponseDto(traverseCopy(parentMessageReceivers.getContent()), pageable.getPageNumber(), parentMessageReceivers.getTotalElements(), parentMessageReceivers.getTotalPages());
    }

    public List<ParentMessageReceiverDtoGet> getAllByReadStatusUser(boolean isRead, Long receiverId) {
        User user = userRepository.findById(receiverId).orElseThrow(() -> new NotFoundException("User Receiver"));
        List<ParentMessageReceiver> parentMessageReceivers = parentMessageReceiverRepository.findByIsReadAndReceiver(isRead, user);
        return traverseCopy(parentMessageReceivers);
    }


    public PaginatedResponseDto getAllByReadStatusUser(boolean isRead, Long receiverId,Pageable pageable) {
        User user = userRepository.findById(receiverId).orElseThrow(() -> new NotFoundException("User Receiver"));
        Page<ParentMessageReceiver> parentMessageReceivers = parentMessageReceiverRepository.findByIsReadAndReceiver(isRead, user,pageable);
        return new PaginatedResponseDto(traverseCopy(parentMessageReceivers.getContent()), pageable.getPageNumber(), parentMessageReceivers.getTotalElements(), parentMessageReceivers.getTotalPages());
    }


    public List<ParentMessageReceiverDtoGet> traverseCopy(List<ParentMessageReceiver> parentMessageReceivers) {
        List<ParentMessageReceiverDtoGet> parentMessageReceiverDtoGetList = new ArrayList<>();
        for (ParentMessageReceiver item : parentMessageReceivers) {
            parentMessageReceiverDtoGetList.add(new ParentMessageReceiverDtoGet(item));
        }
        return parentMessageReceiverDtoGetList;
    }

    public List<ParentMessageReceiverDtoGet> getAllByUser(Long userReceiverId) {
        User user = userRepository.findById(userReceiverId).orElseThrow(() -> new NotFoundException("User Receiver"));
        List<ParentMessageReceiver> parentMessageReceivers = parentMessageReceiverRepository.findAllByReceiver(user);
        List<ParentMessageReceiverDtoGet> parentMessageReceiverDtoGetList = new ArrayList<>();
        return traverseCopy(parentMessageReceivers);
    }

    public PaginatedResponseDto getAllByUser(Long userReceiverId,Pageable pageable) {
        User user = userRepository.findById(userReceiverId).orElseThrow(() -> new NotFoundException("User Receiver"));
        Page<ParentMessageReceiver> parentMessageReceivers = parentMessageReceiverRepository.findAllByReceiver(user,pageable);
        return new PaginatedResponseDto(traverseCopy(parentMessageReceivers.getContent()), pageable.getPageNumber(), parentMessageReceivers.getTotalElements(), parentMessageReceivers.getTotalPages());
    }

    public List<ParentMessageReceiverDtoGet> getChatByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User"));
        List<ParentMessageReceiver> chat = parentMessageReceiverRepository.findAllByReceiverOrParentMessage_Sender(user,user);
        Iterator<ParentMessageReceiver> itr = chat.iterator();

        List<ParentMessageReceiver> new_chat = new ArrayList<>();

        int found = 0;
        for (ParentMessageReceiver message : chat) {
            if (message.getParentMessage().getMessageStatus() == MessageStatus.ALL) {

                if(found == 0){
                    new_chat.add(message);
                    found=1;
                }
            }else {
                found = 0;
                new_chat.add(message);
            }
        }

        return traverseCopy(new_chat);
    }

    public PaginatedResponseDto getChatByUser(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User"));
        Page<ParentMessageReceiver> chat = parentMessageReceiverRepository.findAllByReceiverOrParentMessage_Sender(user, user, pageable);
        return new PaginatedResponseDto(traverseCopy(chat.getContent()), pageable.getPageNumber(), chat.getTotalElements(), chat.getTotalPages());
    }

}
