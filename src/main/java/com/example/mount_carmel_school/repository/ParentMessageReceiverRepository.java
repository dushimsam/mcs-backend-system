package com.example.mount_carmel_school.repository;

import com.example.mount_carmel_school.model.ParentMessage;
import com.example.mount_carmel_school.model.ParentMessageReceiver;
import com.example.mount_carmel_school.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentMessageReceiverRepository extends JpaRepository<ParentMessageReceiver,Long> {
        public List<ParentMessageReceiver> findByIsReadAndReceiver(boolean isRead, User receiver);
        public Page<ParentMessageReceiver> findByIsReadAndReceiver(boolean isRead, User receiver,Pageable pageable);

        public List<ParentMessageReceiver> findByIsRead(boolean isRead);
        public Page<ParentMessageReceiver> findByIsRead(boolean isRead,Pageable pageable);

        public List<ParentMessageReceiver> findAllByReceiver(User receiver);
        public Page<ParentMessageReceiver> findAllByReceiver(User receiver,Pageable pageable);
        public List<ParentMessageReceiver> findAllByParentMessage(ParentMessage parentMessage);
        public Page<ParentMessageReceiver> findAllByParentMessage(ParentMessage parentMessage,Pageable pageable);

        public List<ParentMessageReceiver> findAllByReceiverOrParentMessage_Sender(User user1,User user2);
        public Page<ParentMessageReceiver> findAllByReceiverOrParentMessage_Sender(User user1,User user2, Pageable pageable);
}
