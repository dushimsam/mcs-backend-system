package com.example.mount_carmel_school.repository;

import com.example.mount_carmel_school.model.ParentMessage;
import com.example.mount_carmel_school.model.ParentMessageReceiver;
import com.example.mount_carmel_school.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentMessageReceiverRepository extends JpaRepository<ParentMessageReceiver,Long> {
        public List<ParentMessageReceiver> findByIsReadAndReceiver(boolean isRead, User receiver);
        public List<ParentMessageReceiver> findByIsRead(boolean isRead);
        public List<ParentMessageReceiver> findAllByReceiver(User receiver);
}
