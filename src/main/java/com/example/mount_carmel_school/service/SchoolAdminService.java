package com.example.mount_carmel_school.service;

import com.example.mount_carmel_school.dto.school_admin_dto.SchoolAdminDtoGet;
import com.example.mount_carmel_school.dto.school_admin_dto.SchoolAdminDtoPost;
import com.example.mount_carmel_school.enums.UserCategory;
import com.example.mount_carmel_school.exception.ApiRequestException;
import com.example.mount_carmel_school.exception.NotFoundException;
import com.example.mount_carmel_school.model.SchoolAdmin;
import com.example.mount_carmel_school.model.User;
import com.example.mount_carmel_school.repository.ParentRepository;
import com.example.mount_carmel_school.repository.SchoolAdminRepository;
import com.example.mount_carmel_school.repository.SchoolEmployeeRepository;
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
public class SchoolAdminService {
    @Autowired
    private SchoolAdminRepository schoolAdminRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SchoolEmployeeRepository schoolEmployeeRepository;

    @Autowired
    private ParentRepository parentRepository;

    public SchoolAdminDtoGet add(SchoolAdminDtoPost schoolAdminDtoPost)  {

        User user = userRepository.findById(schoolAdminDtoPost.getUserId()).orElseThrow(()-> new NotFoundException("User"));

        verifyUser(user);
        SchoolAdmin newSchoolAdmin = new SchoolAdmin();
            BeanUtils.copyProperties(schoolAdminDtoPost,this);
            newSchoolAdmin.setUser(user);
            return  new SchoolAdminDtoGet(schoolAdminRepository.save(newSchoolAdmin));

    }

    public List<SchoolAdminDtoGet> getAll() {
        List<SchoolAdmin> schoolAdmins =  schoolAdminRepository.findAll();
        List<SchoolAdminDtoGet> schoolAdminsGet = new ArrayList<>();
        for (SchoolAdmin item : schoolAdmins){
            schoolAdminsGet.add(new SchoolAdminDtoGet(item));
        }
        return schoolAdminsGet;
    }

    public SchoolAdminDtoGet get(Long id) {
        SchoolAdmin schoolAdmin = schoolAdminRepository.findById(id).orElseThrow(()-> new NotFoundException("SchoolAdmin"));
        if(schoolAdmin != null)
        {
            return new SchoolAdminDtoGet(schoolAdmin);
        }else{
            throw new ApiRequestException("SchoolAdmin Not Found");
        }
    }


    public SchoolAdminDtoGet getByUser(Long id) {

        User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException("User"));
        SchoolAdmin schoolAdmin = schoolAdminRepository.findByUser(user);
        if(schoolAdmin != null)
        {
            return new SchoolAdminDtoGet(schoolAdmin);
        }else{
            throw new NotFoundException("SchoolAdmin");
        }
    }



    public void verifyUser(User user)
    {

        if(user.getCategory() != UserCategory.SCHOOL_ADMIN)
        {
            throw  new ApiRequestException("User should be a SCHOOL_ADMIN");
        }

        if(schoolAdminRepository.findByUser(user) != null)
        {
            throw  new ApiRequestException("One user can not be assigned more than one admin.");
        }else if(parentRepository.findByUser(user) != null){
            throw  new ApiRequestException("This user is assigned on the Parent.");
        }else if(schoolEmployeeRepository.findByUser(user) != null)
        {
            throw  new ApiRequestException("This user is assigned on the Employee.");
        }

    }
    
}
