package com.example.mount_carmel_school.repository;

import com.example.mount_carmel_school.model.SchoolEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolEmployeeRepository  extends JpaRepository<SchoolEmployee,Long> {
}
