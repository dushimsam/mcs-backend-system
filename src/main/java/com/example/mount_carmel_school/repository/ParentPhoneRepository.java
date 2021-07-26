package com.example.mount_carmel_school.repository;

import com.example.mount_carmel_school.model.Parent;
import com.example.mount_carmel_school.model.ParentPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentPhoneRepository extends JpaRepository<ParentPhone,Long> {
 public ParentPhone findParentPhoneByPhone(String phone);
 public ParentPhone findParentPhoneByParentAndPhone(Parent parent, String phone);
 @Query("" +
         "SELECT CASE WHEN COUNT(s) > 0 THEN " +
         "TRUE ELSE FALSE END " +
         "FROM ParentPhone s " +
         "WHERE s.phone = ?1"
 )
 Boolean selectsPhoneExists(String phone);
}
