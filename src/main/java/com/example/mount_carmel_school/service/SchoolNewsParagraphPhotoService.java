package com.example.mount_carmel_school.service;


import com.example.mount_carmel_school.dto.DeleteResponseDto;
import com.example.mount_carmel_school.dto.school_news_paragraph_photo_dto.SchoolNewsParagraphPhotoDtoGet;
import com.example.mount_carmel_school.dto.school_news_paragraph_photo_dto.SchoolNewsParagraphPhotoDtoPost;
import com.example.mount_carmel_school.exception.NotFoundException;
import com.example.mount_carmel_school.model.SchoolNewsParagraph;
import com.example.mount_carmel_school.model.SchoolNewsParagraphPhoto;
import com.example.mount_carmel_school.repository.SchoolNewsParagraphPhotoRepository;
import com.example.mount_carmel_school.repository.SchoolNewsParagraphRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class SchoolNewsParagraphPhotoService {

    @Autowired
    private SchoolNewsParagraphPhotoRepository schoolNewsParagraphPhotoRepository;

    @Autowired
    private SchoolNewsParagraphRepository schoolNewsParagraphRepository;

    public SchoolNewsParagraphPhotoDtoGet add(SchoolNewsParagraphPhotoDtoPost schoolNewsParagraphPhotoDtoPost){
        SchoolNewsParagraph schoolNewsParagraph = schoolNewsParagraphRepository.findById(schoolNewsParagraphPhotoDtoPost.getSchoolNewsParagraphId()).orElseThrow(()->new NotFoundException("School News Paragraph"));
        SchoolNewsParagraphPhoto schoolNewsParagraphPhoto = new SchoolNewsParagraphPhoto();
        BeanUtils.copyProperties(schoolNewsParagraphPhotoDtoPost,schoolNewsParagraphPhoto);
        schoolNewsParagraphPhoto.setSchoolNewsParagraph(schoolNewsParagraph);
        return new SchoolNewsParagraphPhotoDtoGet(schoolNewsParagraphPhotoRepository.save(schoolNewsParagraphPhoto));
    }


    public SchoolNewsParagraphPhotoDtoGet get(Long paragraphPhotoId)
    {
        SchoolNewsParagraphPhoto schoolNewsParagraphPhoto = schoolNewsParagraphPhotoRepository.findById(paragraphPhotoId).orElseThrow(()->new NotFoundException("School News Paragraph Photo"));
        return new SchoolNewsParagraphPhotoDtoGet(schoolNewsParagraphPhoto);
    }

    public List<SchoolNewsParagraphPhotoDtoGet> getAll()
    {
        List<SchoolNewsParagraphPhoto> list1 = schoolNewsParagraphPhotoRepository.findAll();
        List<SchoolNewsParagraphPhotoDtoGet> list2 = new ArrayList<>();
        return  traverseCopy(list1,list2);
    }

    public List<SchoolNewsParagraphPhotoDtoGet> getAllBySchoolNewsParagraph(Long schoolNewsParagraphId)
    {
        SchoolNewsParagraph schoolNewsParagraph = schoolNewsParagraphRepository.findById(schoolNewsParagraphId).orElseThrow(()->new NotFoundException("School News Paragraph"));
        List<SchoolNewsParagraphPhoto> list1 = schoolNewsParagraphPhotoRepository.findAllBySchoolNewsParagraph(schoolNewsParagraph);
        List<SchoolNewsParagraphPhotoDtoGet> list2 = new ArrayList<>();
        return  traverseCopy(list1,list2);
    }

    public List<SchoolNewsParagraphPhotoDtoGet> traverseCopy(List<SchoolNewsParagraphPhoto> list1, List<SchoolNewsParagraphPhotoDtoGet> list2)
    {
        for(SchoolNewsParagraphPhoto item: list1)
        {
            list2.add(new SchoolNewsParagraphPhotoDtoGet(item));
        }
        return list2;
    }


    public DeleteResponseDto delete(Long paragraphPhotoId)
    {
        SchoolNewsParagraphPhoto schoolNewsParagraphPhoto = schoolNewsParagraphPhotoRepository.findById(paragraphPhotoId).orElseThrow(()->new NotFoundException("School News Paragraph Photo"));
        schoolNewsParagraphPhotoRepository.delete(schoolNewsParagraphPhoto);
        return new DeleteResponseDto("PICTURE DELETED SUCCESSFULLY");
    }

    public SchoolNewsParagraphPhotoDtoGet update(Long paragraphPhotoId,SchoolNewsParagraphPhotoDtoPost schoolNewsParagraphPhotoDtoPost){
        SchoolNewsParagraphPhoto schoolNewsParagraphPhoto = schoolNewsParagraphPhotoRepository.findById(paragraphPhotoId).orElseThrow(()->new NotFoundException("School News Paragraph Photo"));
        SchoolNewsParagraph schoolNewsParagraph = schoolNewsParagraphRepository.findById(schoolNewsParagraphPhotoDtoPost.getSchoolNewsParagraphId()).orElseThrow(()->new NotFoundException("School News Paragraph"));
        BeanUtils.copyProperties(schoolNewsParagraphPhotoDtoPost,schoolNewsParagraphPhoto);
        schoolNewsParagraphPhoto.setSchoolNewsParagraph(schoolNewsParagraph);
        return new SchoolNewsParagraphPhotoDtoGet(schoolNewsParagraphPhotoRepository.save(schoolNewsParagraphPhoto));
    }

}
