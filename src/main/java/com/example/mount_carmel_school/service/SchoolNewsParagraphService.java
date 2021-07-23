package com.example.mount_carmel_school.service;

import com.example.mount_carmel_school.dto.DeleteResponseDto;
import com.example.mount_carmel_school.dto.school_news_paragraph_dto.SchoolNewsParagraphDtoGet;
import com.example.mount_carmel_school.dto.school_news_paragraph_dto.SchoolNewsParagraphDtoPost;
import com.example.mount_carmel_school.dto.school_news_paragraph_photo_dto.SchoolNewsParagraphPhotoDtoGet;
import com.example.mount_carmel_school.exception.NotFoundException;
import com.example.mount_carmel_school.model.SchoolNews;
import com.example.mount_carmel_school.model.SchoolNewsParagraph;
import com.example.mount_carmel_school.model.SchoolNewsParagraphPhoto;
import com.example.mount_carmel_school.repository.SchoolNewsParagraphRepository;
import com.example.mount_carmel_school.repository.SchoolNewsRepository;
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
public class SchoolNewsParagraphService {

    @Autowired
    private SchoolNewsRepository schoolNewsRepository;

    @Autowired
    private SchoolNewsParagraphRepository schoolNewsParagraphRepository;

    public SchoolNewsParagraphDtoGet add(SchoolNewsParagraphDtoPost schoolNewsParagraphDtoPost){
        SchoolNews schoolNews = schoolNewsRepository.findById(schoolNewsParagraphDtoPost.getSchoolNewsId()).orElseThrow(()->new NotFoundException("School News"));
        SchoolNewsParagraph schoolNewsParagraph = new SchoolNewsParagraph();
        BeanUtils.copyProperties(schoolNewsParagraphDtoPost,schoolNewsParagraph);
        schoolNewsParagraph.setSchoolNews(schoolNews);
        return new SchoolNewsParagraphDtoGet(schoolNewsParagraphRepository.save(schoolNewsParagraph));
    }

    public SchoolNewsParagraphDtoGet get(Long paragraphId)
    {
        SchoolNewsParagraph schoolNewsParagraph = schoolNewsParagraphRepository.findById(paragraphId).orElseThrow(()->new NotFoundException("School News Paragraph"));
        return new SchoolNewsParagraphDtoGet(schoolNewsParagraph);
    }

    public SchoolNewsParagraphDtoGet update(SchoolNewsParagraphDtoPost schoolNewsParagraphDtoPost,Long paragraphId)
    {
        SchoolNews schoolNews = schoolNewsRepository.findById(schoolNewsParagraphDtoPost.getSchoolNewsId()).orElseThrow(()->new NotFoundException("School News"));
        SchoolNewsParagraph schoolNewsParagraph = schoolNewsParagraphRepository.findById(paragraphId).orElseThrow(()->new NotFoundException("School News Paragraph"));
        BeanUtils.copyProperties(schoolNewsParagraphDtoPost,schoolNewsParagraph);
        schoolNewsParagraph.setSchoolNews(schoolNews);
        return new SchoolNewsParagraphDtoGet(schoolNewsParagraph);
    }

    public List<SchoolNewsParagraphDtoGet> getAll()
    {
        List<SchoolNewsParagraph> list1 = schoolNewsParagraphRepository.findAll();
        List<SchoolNewsParagraphDtoGet> list2 = new ArrayList<>();
      return  traverseCopy(list1,list2);
    }

    public List<SchoolNewsParagraphDtoGet> findAllSchoolNews(Long schoolNewsId)
    {
        SchoolNews schoolNews = schoolNewsRepository.findById(schoolNewsId).orElseThrow(()->new NotFoundException("School News"));
        List<SchoolNewsParagraph> list1 = schoolNewsParagraphRepository.findAllBySchoolNews(schoolNews);
        List<SchoolNewsParagraphDtoGet> list2 = new ArrayList<>();
        return  traverseCopy(list1,list2);
    }

    public DeleteResponseDto delete(Long paragraphId)
    {
        SchoolNewsParagraph schoolNewsParagraph = schoolNewsParagraphRepository.findById(paragraphId).orElseThrow(()->new NotFoundException("School News Paragraph"));
        schoolNewsParagraphRepository.delete(schoolNewsParagraph);
        return new DeleteResponseDto(schoolNewsParagraph);
    }

    public List<SchoolNewsParagraphDtoGet> traverseCopy(List<SchoolNewsParagraph> list1, List<SchoolNewsParagraphDtoGet> list2)
    {
        for(SchoolNewsParagraph item: list1)
        {
            list2.add(new SchoolNewsParagraphDtoGet(item));
        }
        return list2;
    }


    public void addDirect(SchoolNewsParagraphDtoPost schoolNewsParagraphDtoPost,SchoolNews schoolNews){
        SchoolNewsParagraph schoolNewsParagraph = new SchoolNewsParagraph();
        BeanUtils.copyProperties(schoolNewsParagraphDtoPost,schoolNewsParagraph);
        schoolNewsParagraph.setSchoolNews(schoolNews);
        schoolNewsParagraphRepository.save(schoolNewsParagraph);
    }


}
