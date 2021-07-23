package com.example.mount_carmel_school.service;

import com.example.mount_carmel_school.dto.parent_dto.ParentDtoGet;
import com.example.mount_carmel_school.dto.parent_dto.ParentDtoPost;
import com.example.mount_carmel_school.dto.parent_phone.ParentPhoneDtoPost;
import com.example.mount_carmel_school.exception.ApiRequestException;
import com.example.mount_carmel_school.model.Parent;
import com.example.mount_carmel_school.model.Student;
import com.example.mount_carmel_school.model.User;
import com.example.mount_carmel_school.repository.ParentPhoneRepository;
import com.example.mount_carmel_school.repository.ParentRepository;
import com.example.mount_carmel_school.repository.StudentRepository;
import com.example.mount_carmel_school.repository.UserRepository;
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
public class ParentService {
    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ParentPhoneRepository parentPhoneRepository;

    @Autowired
    private ParentPhoneService parentPhoneService;

    public ParentDtoGet add(ParentDtoPost parentDtoPost)  {

        User user = userRepository.findById(parentDtoPost.getUserId()).get();
        Parent newParent = new Parent();

        if(user == null)
        {
            throw new ApiRequestException("User Not Found");
        }else{
            BeanUtils.copyProperties(parentDtoPost,newParent,"userId","parentPhonePosts","schoolId");
            newParent.setUser(user);
            Parent savedParent = parentRepository.save(newParent);
            handleNewParentPhones(savedParent,parentDtoPost.getParentPhonePosts());
            return  get(savedParent.getId());
        }

    }

    public void handleNewParentPhones(Parent parent,List<ParentPhoneDtoPost> parentPhonePosts)
    {
        for(ParentPhoneDtoPost phonePost :parentPhonePosts)
        {
            parentPhoneService.addPhoneToParent(phonePost,parent.getId());
        }
    }


    public List<ParentDtoGet> getAll() {
        List<Parent> parents =  parentRepository.findAll();
        List<ParentDtoGet> parentsDto = new ArrayList<>();
        for (Parent item : parents){
            parentsDto.add(new ParentDtoGet(item));
        }
        return parentsDto;
    }

    public ParentDtoGet get(Long id) {
        Parent parent = parentRepository.findById(id).get();
        if(parent != null)
        {
            return new ParentDtoGet(parent);
        }else{
            throw new ApiRequestException("Parent Not Found");
        }
    }


    public ParentDtoGet addStudentToParent(Long parentId,Long studentId)
    {
   Parent parent = parentRepository.findById(parentId).get();
   Student student = studentRepository.findById(studentId).get();
   parent.addStudent(student);
   return new ParentDtoGet(parentRepository.save(parent));
    }

}
