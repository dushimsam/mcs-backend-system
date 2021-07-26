package com.example.mount_carmel_school.service;


import com.example.mount_carmel_school.dto.parent_message_receiver_dto.ParentMessageReceiverDtoGet;
import com.example.mount_carmel_school.dto.parent_message_receiver_dto.ParentMessageReceiverDtoPost;
import com.example.mount_carmel_school.exception.NotFoundException;
import com.example.mount_carmel_school.model.ParentMessage;
import com.example.mount_carmel_school.model.ParentMessageReceiver;
import com.example.mount_carmel_school.model.User;
import com.example.mount_carmel_school.repository.ParentMessageReceiverRepository;
import com.example.mount_carmel_school.repository.ParentMessageRepository;
import com.example.mount_carmel_school.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public ParentMessageReceiverDtoGet add(ParentMessageReceiverDtoPost parentMessageReceiverDtoPost)  {
        ParentMessageReceiver parentMessageReceiver = new ParentMessageReceiver();
        User receiver = userRepository.findById(parentMessageReceiverDtoPost.getReceiver_userId()).orElseThrow(()-> new NotFoundException("User"));
        ParentMessage parentMessage = messageRepository.findById(parentMessageReceiverDtoPost.getParentMessageId()).orElseThrow(()-> new NotFoundException("Parent Message"));
        parentMessageReceiver.setReceiver(receiver);
        parentMessageReceiver.setParentMessage(parentMessage);
        return new ParentMessageReceiverDtoGet(parentMessageReceiverRepository.save(parentMessageReceiver));
    }

    public List<ParentMessageReceiverDtoGet> getAll()
    {
        List<ParentMessageReceiver> parentMessageReceivers = parentMessageReceiverRepository.findAll();
        List<ParentMessageReceiverDtoGet> parentMessageReceiverDtoGetList = new ArrayList<>();
        return traverseCopy(parentMessageReceivers,parentMessageReceiverDtoGetList);
    }



    public List<ParentMessageReceiverDtoGet> getAllByParentMessage(Long parentMessageId)
    {
        ParentMessage parentMessage = messageRepository.findById(parentMessageId).orElseThrow(()->new NotFoundException("ParentMessage"));
        List<ParentMessageReceiver> parentMessageReceivers = parentMessageReceiverRepository.findAllByParentMessage(parentMessage);
        List<ParentMessageReceiverDtoGet> parentMessageReceiverDtoGetList = new ArrayList<>();
        return traverseCopy(parentMessageReceivers,parentMessageReceiverDtoGetList);
    }


    public ParentMessageReceiverDtoGet get(Long receiverId)
    {
        ParentMessageReceiver parentMessageReceiver = parentMessageReceiverRepository.findById(receiverId).orElseThrow(()-> new NotFoundException("Parent Message Receiver"));
        return  new ParentMessageReceiverDtoGet(parentMessageReceiver);
    }


    public ParentMessageReceiverDtoGet markAsRead(Long receiverId)
    {
        ParentMessageReceiver parentMessageReceiver = parentMessageReceiverRepository.findById(receiverId).orElseThrow(()-> new NotFoundException("ParentMessageReceiver"));
        parentMessageReceiver.setIsRead(true);
        return  new ParentMessageReceiverDtoGet(parentMessageReceiverRepository.save(parentMessageReceiver));
    }

   public List<ParentMessageReceiverDtoGet> getAllByReadStatus(boolean isRead)
    {
        List<ParentMessageReceiver> parentMessageReceivers = parentMessageReceiverRepository.findByIsRead(isRead);
        List<ParentMessageReceiverDtoGet> parentMessageReceiverDtoGetList = new ArrayList<>();
        return traverseCopy(parentMessageReceivers,parentMessageReceiverDtoGetList);
    }

  public  List<ParentMessageReceiverDtoGet> getAllByReadStatusUser(boolean isRead,Long receiverId)
    {
        User user = userRepository.findById(receiverId).orElseThrow(()->new NotFoundException("User Receiver"));
        List<ParentMessageReceiver> parentMessageReceivers = parentMessageReceiverRepository.findByIsReadAndReceiver(isRead,user);
        List<ParentMessageReceiverDtoGet> parentMessageReceiverDtoGetList = new ArrayList<>();
        return traverseCopy(parentMessageReceivers,parentMessageReceiverDtoGetList);
    }

   public  List<ParentMessageReceiverDtoGet> traverseCopy(List<ParentMessageReceiver> parentMessageReceivers,List<ParentMessageReceiverDtoGet> parentMessageReceiverDtoGetList)
    {
        for(ParentMessageReceiver item:parentMessageReceivers)
        {
            parentMessageReceiverDtoGetList.add(new ParentMessageReceiverDtoGet(item));
        }
        return parentMessageReceiverDtoGetList;
    }

    public  List<ParentMessageReceiverDtoGet>  getAllByUser(Long userReceiverId) {
        User user = userRepository.findById(userReceiverId).orElseThrow(()->new NotFoundException("User Receiver"));
        List<ParentMessageReceiver> parentMessageReceivers = parentMessageReceiverRepository.findAllByReceiver(user);
        List<ParentMessageReceiverDtoGet> parentMessageReceiverDtoGetList = new ArrayList<>();
        return traverseCopy(parentMessageReceivers,parentMessageReceiverDtoGetList);
    }

}
