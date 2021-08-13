package com.example.mount_carmel_school.repository;

import com.example.mount_carmel_school.model.ParentMessage;
import com.example.mount_carmel_school.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentMessageRepository extends JpaRepository<ParentMessage,Long> {
    public List<ParentMessage> findAllBySender(User user);
    public Page<ParentMessage> findAllBySender(User user, Pageable pageable);
}
