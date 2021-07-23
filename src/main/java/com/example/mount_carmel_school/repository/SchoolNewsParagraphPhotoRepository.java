package com.example.mount_carmel_school.repository;

import com.example.mount_carmel_school.model.SchoolNewsParagraph;
import com.example.mount_carmel_school.model.SchoolNewsParagraphPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolNewsParagraphPhotoRepository extends JpaRepository<SchoolNewsParagraphPhoto,Long> {
    public List<SchoolNewsParagraphPhoto> findAllBySchoolNewsParagraph(SchoolNewsParagraph schoolNewsParagraph);
}
