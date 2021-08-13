package com.example.mount_carmel_school.repository;


import com.example.mount_carmel_school.model.SchoolNews;
import com.example.mount_carmel_school.model.global.SchoolNewsPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolNewsPhotoRepository  extends JpaRepository<SchoolNewsPhoto,Long> {
    List<SchoolNewsPhoto>  findAllBySchoolNews(SchoolNews schoolNews);
}
