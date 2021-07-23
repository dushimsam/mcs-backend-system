package com.example.mount_carmel_school.repository;

import com.example.mount_carmel_school.model.SchoolNews;
import com.example.mount_carmel_school.model.SchoolNewsParagraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolNewsParagraphRepository extends JpaRepository<SchoolNewsParagraph,Long> {
public List<SchoolNewsParagraph> findAllBySchoolNews(SchoolNews schoolNews);
}
