package com.example.mount_carmel_school.service;

import com.example.mount_carmel_school.dto.DeleteResponseDto;

import com.example.mount_carmel_school.dto.school_news_dto.SchoolNewsDtoGet;
import com.example.mount_carmel_school.dto.school_news_dto.SchoolNewsDtoPost;
import com.example.mount_carmel_school.exception.NotFoundException;
import com.example.mount_carmel_school.model.SchoolNews;
import com.example.mount_carmel_school.model.SchoolEmployee;
import com.example.mount_carmel_school.repository.SchoolEmployeeRepository;
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
public class SchoolNewsService {

    @Autowired
    private SchoolNewsRepository schoolNewsRepository;

    @Autowired
    private SchoolEmployeeRepository schoolEmployeeRepository;

    @Autowired
    private SchoolNewsParagraphRepository schoolNewsParagraphRepository;


    @Autowired
    private SchoolNewsParagraphService schoolNewsParagraphService;

//    @Autowired
//    private SchoolNewsReceiverRepository schoolNewsReceiverRepository;


    public SchoolNewsDtoGet add(SchoolNewsDtoPost schoolNewsDtoPost){
        SchoolEmployee schoolEmployee = schoolEmployeeRepository.findById(schoolNewsDtoPost.getPostedBy_employeeId()).orElseThrow(()->new NotFoundException("SchoolEmployee"));
        SchoolNews schoolNews = new SchoolNews();
        BeanUtils.copyProperties(schoolNewsDtoPost,schoolNews);
        schoolNews.setSchoolEmployee(schoolEmployee);
        SchoolNews newSchoolNews = schoolNewsRepository.save(schoolNews);
        return new SchoolNewsDtoGet(newSchoolNews);
    }


    public List<SchoolNewsDtoGet> getAll()
    {
        List<SchoolNews> schoolNews = schoolNewsRepository.findAll();
        List<SchoolNewsDtoGet> formattedSchoolNews = new ArrayList<>();
        return traverseCopy(schoolNews, formattedSchoolNews);
    }

    public SchoolNewsDtoGet get(Long messageId)
    {
        SchoolNews schoolNews = schoolNewsRepository.findById(messageId).orElseThrow(()->new NotFoundException("Message"));
        return new SchoolNewsDtoGet(schoolNews);
    }


    public SchoolNewsDtoGet update(Long schoolNewsId,SchoolNewsDtoPost schoolNewsDtoPost){
        SchoolEmployee schoolEmployee = schoolEmployeeRepository.findById(schoolNewsDtoPost.getPostedBy_employeeId()).orElseThrow(()->new NotFoundException("SchoolEmployee"));
        SchoolNews schoolNews = schoolNewsRepository.findById(schoolNewsId).orElseThrow(()->new NotFoundException("SchoolNews"));
        BeanUtils.copyProperties(schoolNewsDtoPost,schoolNews,"postedBy_employeeId");
        schoolNews.setSchoolEmployee(schoolEmployee);
        return new SchoolNewsDtoGet(schoolNews);
    }

    public DeleteResponseDto delete(Long schoolNewsId) {
        SchoolNews schoolNews = schoolNewsRepository.findById(schoolNewsId).orElseThrow(()->new NotFoundException("SchoolNews"));
        schoolNewsRepository.delete(schoolNews);
        return  new DeleteResponseDto("DELETED SUCCESSFULLY");
    }


    public  List<SchoolNewsDtoGet>  getAllBySchoolEmployee(Long schoolEmployeeId) {
        SchoolEmployee schoolEmployee = schoolEmployeeRepository.findById(schoolEmployeeId).orElseThrow(()->new NotFoundException("SchoolEmployee"));
        List<SchoolNews> schoolNews = schoolNewsRepository.findAllBySchoolEmployee(schoolEmployee);
        List<SchoolNewsDtoGet> schoolNewsDtoGetList = new ArrayList<>();
        return traverseCopy(schoolNews, schoolNewsDtoGetList);
    }

    public  List<SchoolNewsDtoGet> traverseCopy(List<SchoolNews> list1, List<SchoolNewsDtoGet> list2)
    {
        for(SchoolNews item: list1)
        {
            list2.add(new SchoolNewsDtoGet(item));
        }
        return list2;
    }
    
}
