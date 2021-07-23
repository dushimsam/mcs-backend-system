package com.example.mount_carmel_school.service;

import com.example.mount_carmel_school.dto.school_admin_dto.SchoolAdminDtoGet;
import com.example.mount_carmel_school.dto.school_admin_dto.SchoolAdminDtoPost;
import com.example.mount_carmel_school.exception.ApiRequestException;
import com.example.mount_carmel_school.model.SchoolAdmin;
import com.example.mount_carmel_school.model.User;
import com.example.mount_carmel_school.repository.SchoolAdminRepository;
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


    public SchoolAdminDtoGet add(SchoolAdminDtoPost schoolAdminDtoPost)  {

        User user = userRepository.findById(schoolAdminDtoPost.getUserId()).get();

        SchoolAdmin newSchoolAdmin = new SchoolAdmin();

        if(user == null)
        {
            throw new ApiRequestException("User Not Found");
        }else{
            BeanUtils.copyProperties(schoolAdminDtoPost, newSchoolAdmin,"userId");
            newSchoolAdmin.setUser(user);
            return  new SchoolAdminDtoGet(schoolAdminRepository.save(newSchoolAdmin));
        }
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
        SchoolAdmin schoolAdmin = schoolAdminRepository.findById(id).get();
        if(schoolAdmin != null)
        {
            return new SchoolAdminDtoGet(schoolAdmin);
        }else{
            throw new ApiRequestException("SchoolAdmin Not Found");
        }
    }
    
}
