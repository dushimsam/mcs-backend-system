package com.example.mount_carmel_school.service;


import com.example.mount_carmel_school.dto.school_employee_dto.SchoolEmployeeDtoGet;
import com.example.mount_carmel_school.dto.school_employee_dto.SchoolEmployeeDtoPost;
import com.example.mount_carmel_school.exception.ApiRequestException;
import com.example.mount_carmel_school.model.SchoolEmployee;
import com.example.mount_carmel_school.model.User;
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
public class SchoolEmployeeService {
    @Autowired
    private SchoolEmployeeRepository employeeRepository;
    @Autowired
    private UserRepository userRepository;



    public SchoolEmployeeDtoGet add(SchoolEmployeeDtoPost schoolEmployeeDtoPost)  {

        User user = userRepository.findById(schoolEmployeeDtoPost.getUserId()).get();

        SchoolEmployee schoolEmployee = new SchoolEmployee();

        if(user == null)
        {
            throw new ApiRequestException("User Not Found");
        }else{
            BeanUtils.copyProperties(schoolEmployeeDtoPost, schoolEmployee,"userId");
            schoolEmployee.setUser(user);
            return  new SchoolEmployeeDtoGet(employeeRepository.save(schoolEmployee));
        }
    }

    public List<SchoolEmployeeDtoGet> getAll() {
        List<SchoolEmployee> schoolEmployees =  employeeRepository.findAll();
        List<SchoolEmployeeDtoGet> schoolEmployeesGet = new ArrayList<>();
        for (SchoolEmployee item : schoolEmployees){
            schoolEmployeesGet.add(new SchoolEmployeeDtoGet(item));
        }
        return schoolEmployeesGet;
    }

    public SchoolEmployeeDtoGet get(Long id) {
        SchoolEmployee schoolEmployee = employeeRepository.findById(id).get();
        if(schoolEmployee != null)
        {
            return new SchoolEmployeeDtoGet(schoolEmployee);
        }else{
            throw new ApiRequestException("SchoolEmployee Not Found");
        }
    }



}
