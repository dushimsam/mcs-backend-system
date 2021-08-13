package com.example.mount_carmel_school.service;

import com.example.mount_carmel_school.dto.DeleteResponseDto;
import com.example.mount_carmel_school.dto.school_news_dto.SchoolNewsDtoGet;
import com.example.mount_carmel_school.dto.school_news_dto.SchoolNewsDtoPost;
import com.example.mount_carmel_school.dto.school_news_photos_dto.SchoolNewsPhotoDtoGet;
import com.example.mount_carmel_school.dto.school_news_photos_dto.SchoolNewsPhotoDtoPost;
import com.example.mount_carmel_school.dto.student_dto.StudentDtoGet;
import com.example.mount_carmel_school.exception.NotFoundException;
import com.example.mount_carmel_school.model.SchoolEmployee;
import com.example.mount_carmel_school.model.SchoolNews;
import com.example.mount_carmel_school.model.Student;
import com.example.mount_carmel_school.model.global.SchoolNewsPhoto;
import com.example.mount_carmel_school.repository.SchoolNewsPhotoRepository;
import com.example.mount_carmel_school.repository.SchoolNewsRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class SchoolNewsPhotoService {
    @Autowired
    private SchoolNewsRepository schoolNewsRepository;
    @Autowired
    private SchoolNewsPhotoRepository schoolNewsPhotoRepository;

    public SchoolNewsPhotoDtoGet add(SchoolNewsPhotoDtoPost schoolNewsDtoPost){
        SchoolNews news = schoolNewsRepository.findById(schoolNewsDtoPost.getNewsId()).orElseThrow(()->new NotFoundException("SchoolNews"));
        SchoolNewsPhoto photo = new SchoolNewsPhoto();
        BeanUtils.copyProperties(schoolNewsDtoPost,photo);
        photo.setSchoolNews(news);
        return new SchoolNewsPhotoDtoGet(schoolNewsPhotoRepository.save(photo));
    }

    public SchoolNewsPhotoDtoGet put(Long id,SchoolNewsPhotoDtoPost schoolNewsDtoPost) {
        SchoolNewsPhoto photo = schoolNewsPhotoRepository.findById(id).orElseThrow(()->new NotFoundException("Photo"));
        SchoolNews news = schoolNewsRepository.findById(schoolNewsDtoPost.getNewsId()).orElseThrow(()->new NotFoundException("SchoolNews"));
        BeanUtils.copyProperties(schoolNewsDtoPost,photo);
        photo.setSchoolNews(news);
        return new SchoolNewsPhotoDtoGet(schoolNewsPhotoRepository.save(photo));
    }


    public SchoolNewsPhotoDtoGet get(Long id) {
        SchoolNewsPhoto schoolNewsPhoto = schoolNewsPhotoRepository.findById(id).orElseThrow(()->new NotFoundException("Photo"));
        return new SchoolNewsPhotoDtoGet(schoolNewsPhoto);
    }


    public DeleteResponseDto delete(Long id) {
        SchoolNewsPhoto photo = schoolNewsPhotoRepository.findById(id).orElseThrow(()->new NotFoundException("Photo"));
        schoolNewsPhotoRepository.delete(photo);
        return new DeleteResponseDto(photo);
    }
}
