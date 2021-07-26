package com.example.mount_carmel_school.service;

import com.example.mount_carmel_school.dto.parent_dto.ParentDtoGet;
import com.example.mount_carmel_school.dto.parent_dto.ParentDtoPost;
import com.example.mount_carmel_school.dto.parent_phone.ParentPhoneDtoPost;
import com.example.mount_carmel_school.enums.UserCategory;
import com.example.mount_carmel_school.exception.ApiRequestException;
import com.example.mount_carmel_school.exception.NotFoundException;
import com.example.mount_carmel_school.model.Parent;
import com.example.mount_carmel_school.model.Student;
import com.example.mount_carmel_school.model.User;
import com.example.mount_carmel_school.repository.*;
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
    private SchoolAdminRepository schoolAdminRepository;

    @Autowired
    private SchoolEmployeeRepository schoolEmployeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ParentPhoneRepository parentPhoneRepository;

    @Autowired
    private ParentPhoneService parentPhoneService;

    public ParentDtoGet add(ParentDtoPost parentDtoPost)  {
            User user = userRepository.findById(parentDtoPost.getUserId()).orElseThrow(()-> new NotFoundException("User"));
            Parent newParent = new Parent();
            verifyUser(user);
            BeanUtils.copyProperties(parentDtoPost,newParent);
            newParent.setUser(user);
            Parent savedParent = parentRepository.save(newParent);
            parentPhoneService.addPhoneToParent(new ParentPhoneDtoPost(parentDtoPost.getPhone()),savedParent.getId());
            return  get(savedParent.getId());
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
        Parent parent = parentRepository.findById(id).orElseThrow(() -> new NotFoundException("Parent"));
        return new ParentDtoGet(parent);
    }

    public List<ParentDtoGet> getByStatus(String status) {

        if(!status.equals("ACTIVE") && !status.equals("INACTIVE"))
        {
            throw  new ApiRequestException("STATUS SHOULD BE EITHER ACTIVE OR INACTIVE");
        }
        List<User> users = userRepository.findByIsLockedAndCategory(!status.equals("ACTIVE"), UserCategory.PARENT);
        List<ParentDtoGet> parents = new ArrayList<>();

        for(User user:users)
        {
            parents.add(new ParentDtoGet(parentRepository.findByUser(user)));
        }
        return parents;
    }



    public ParentDtoGet getByUser(Long id) {

        User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException("User"));
        Parent parent = parentRepository.findByUser(user);
        if(parent != null)
        {
            return new ParentDtoGet(parent);
        }else{
            throw new NotFoundException("Parent");
        }
    }

    public ParentDtoGet addStudentToParent(Long parentId,Long studentId)
    {
   Parent parent = parentRepository.findById(parentId).orElseThrow(()-> new NotFoundException("Parent"));
   if(parent.getUser().getIsLocked())
   {
       throw new ApiRequestException("The parent is not active");
   }

   Student student = studentRepository.findById(studentId).orElseThrow(()-> new NotFoundException("Student"));
        if(parentRepository.findByStudents(student) != null)
        {
            throw new ApiRequestException("The Student already added on this parent");
        }
   parent.addStudent(student);
   return new ParentDtoGet(parentRepository.save(parent));
    }

    public void verifyUser(User user)
    {

        if(user.getCategory() != UserCategory.PARENT)
        {
            throw  new ApiRequestException("User should be a parent");
        }

         if(parentRepository.findByUser(user) != null)
    {
        throw  new ApiRequestException("One user can not be assigned more than one parent.");
    }else if(schoolEmployeeRepository.findByUser(user) != null){
             throw  new ApiRequestException("This user is assigned on the Employee.");
    }else if(schoolAdminRepository.findByUser(user) != null)
         {
             throw  new ApiRequestException("This user is assigned on the Admin.");
         }

    }
}
