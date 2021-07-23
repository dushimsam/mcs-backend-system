package com.example.mount_carmel_school.repository;
import com.example.mount_carmel_school.enums.UserCategory;
import com.example.mount_carmel_school.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
    public User findByUserName(String username);
    public List<User> findAllByCategory(UserCategory category);
}
