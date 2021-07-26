package com.example.mount_carmel_school.repository;
import com.example.mount_carmel_school.model.Parent;
import com.example.mount_carmel_school.model.Student;
import com.example.mount_carmel_school.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentRepository extends JpaRepository<Parent,Long> {
  public Parent findByUser(User user);
  public Parent findByStudents(Student student);
}
