package com.example.mount_carmel_school.repository;

import com.example.mount_carmel_school.model.SchoolAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolAdminRepository extends JpaRepository<SchoolAdmin,Long> {
}
