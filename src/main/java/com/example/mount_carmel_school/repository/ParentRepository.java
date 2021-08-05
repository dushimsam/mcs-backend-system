package com.example.mount_carmel_school.repository;
import com.example.mount_carmel_school.model.Parent;
import com.example.mount_carmel_school.model.Student;
import com.example.mount_carmel_school.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentRepository extends JpaRepository<Parent,Long> {
  public Parent findParentByUser(User user);
  public Parent findByStudents(Student student);
  public List<Parent> findAllByUser_IsLocked(boolean lockStatus);
  public Page<Parent> findAllByUser_IsLocked(boolean confirmStatus, Pageable pageable);
  public Page<Parent> findAllByUser_IsConfirmed(boolean confirmStatus, Pageable pageable);
  public List<Parent> findAllByUser_IsConfirmed(boolean confirmStatus);
  public Page<Parent> findByUser_FirstNameContainingAndUser_LastNameContainingAndUser_EmailContaining(String firName,String lastName,String email, Pageable pageable);
  public Page<Parent> findByUser_FirstNameContainingAndUser_LastNameContainingAndUser_EmailContainingAndUser_IsConfirmed(String firName,String lastName,String email,boolean confirmStatus, Pageable pageable);

}
