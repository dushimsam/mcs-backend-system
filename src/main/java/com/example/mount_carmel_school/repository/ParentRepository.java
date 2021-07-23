package com.example.mount_carmel_school.repository;
import com.example.mount_carmel_school.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentRepository extends JpaRepository<Parent,Long> {
    public List<Parent> findBySchool(Long schoolId);
}
