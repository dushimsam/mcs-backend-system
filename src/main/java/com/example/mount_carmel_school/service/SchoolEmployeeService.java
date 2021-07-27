package com.example.mount_carmel_school.service;


import com.example.mount_carmel_school.dto.school_employee_dto.SchoolEmployeeDtoGet;
import com.example.mount_carmel_school.dto.school_employee_dto.SchoolEmployeeDtoPost;
import com.example.mount_carmel_school.enums.UserCategory;
import com.example.mount_carmel_school.exception.ApiRequestException;
import com.example.mount_carmel_school.exception.NotFoundException;
import com.example.mount_carmel_school.model.SchoolEmployee;
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
public class SchoolEmployeeService {
    @Autowired
    private SchoolEmployeeRepository employeeRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private SchoolAdminRepository schoolAdminRepository;


    public SchoolEmployeeDtoGet add(SchoolEmployeeDtoPost schoolEmployeeDtoPost)  {

        User user = userRepository.findById(schoolEmployeeDtoPost.getUserId()).orElseThrow(()->new NotFoundException("User"));

        SchoolEmployee schoolEmployee = new SchoolEmployee();

            verifyUser(user);

            BeanUtils.copyProperties(schoolEmployeeDtoPost, schoolEmployee,"userId");
            schoolEmployee.setUser(user);
            return  new SchoolEmployeeDtoGet(employeeRepository.save(schoolEmployee));

    }

    public SchoolEmployeeDtoGet update(Long id,SchoolEmployeeDtoPost schoolEmployeeDtoPost)  {

        SchoolEmployee schoolEmployee = employeeRepository.findById(id).orElseThrow(()->new NotFoundException("SchoolEmployee"));
        User user = userRepository.findById(schoolEmployeeDtoPost.getUserId()).orElseThrow(()->new NotFoundException("User"));

        if(schoolEmployee.getUser().getId() != schoolEmployee.getUser().getId())
        {
            throw new ApiRequestException("Can not update the user");
        }

        BeanUtils.copyProperties(schoolEmployeeDtoPost, schoolEmployee);
        schoolEmployee.setUser(user);
        return  new SchoolEmployeeDtoGet(employeeRepository.save(schoolEmployee));

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


    public SchoolEmployeeDtoGet getByUser(Long id) {

        User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException("User"));
        SchoolEmployee schoolEmployee = employeeRepository.findByUser(user);
        if(schoolEmployee != null)
        {
            return new SchoolEmployeeDtoGet(schoolEmployee);
        }else{
            throw new NotFoundException("SchoolEmployee");
        }
    }


    public void verifyUser(User user)
    {

        if(user.getCategory() != UserCategory.SCHOOL_EMPLOYEE)
        {
            throw  new ApiRequestException("User should be a SCHOOL_EMPLOYEE");
        }

        if(employeeRepository.findByUser(user) != null)
        {
            throw  new ApiRequestException("One user can not be assigned more than one employee.");
        }else if(parentRepository.findParentByUser(user) != null){
            throw  new ApiRequestException("This user is assigned on the Parent.");
        }else if(schoolAdminRepository.findByUser(user) != null)
        {
            throw  new ApiRequestException("This user is assigned on the Admin.");
        }

    }

    public List<SchoolEmployeeDtoGet> getByStatus(String status) {

        if(!status.equals("ACTIVE") && !status.equals("INACTIVE"))
        {
            throw  new ApiRequestException("STATUS SHOULD BE EITHER ACTIVE OR INACTIVE");
        }
        List<User> users = userRepository.findAllByIsLockedAndCategory(!status.equals("ACTIVE"), UserCategory.SCHOOL_EMPLOYEE);
        List<SchoolEmployeeDtoGet> employees = new ArrayList<>();

        for(User user:users)
        {
            employees.add(new SchoolEmployeeDtoGet(employeeRepository.findByUser(user)));
        }
        return employees;
    }
}
