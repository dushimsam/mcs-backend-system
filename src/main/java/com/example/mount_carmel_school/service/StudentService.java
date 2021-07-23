package com.example.mount_carmel_school.service;
import com.example.mount_carmel_school.dto.student_dto.StudentDtoGet;
import com.example.mount_carmel_school.dto.student_dto.StudentDtoPost;
import com.example.mount_carmel_school.exception.ApiRequestException;
import com.example.mount_carmel_school.model.Student;
import com.example.mount_carmel_school.model.User;
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
public class StudentService {
    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    public StudentDtoGet add(StudentDtoPost studentDtoPost)  {
        User user = userRepository.findById(studentDtoPost.getUserId()).get();
        Student newStudent = new Student();
        if(user == null)
        {
            throw new ApiRequestException("User Not Found");
        }else{
            BeanUtils.copyProperties(studentDtoPost, newStudent,"userId","parentsId");
            Student savedStudent = studentRepository.save(newStudent);
            return  new StudentDtoGet(savedStudent);
        }
    }


    public List<StudentDtoGet> getAll() {
        List<Student> students =  studentRepository.findAll();
        List<StudentDtoGet> studentsGet = new ArrayList<>();
        for (Student item : students){
            studentsGet.add(new StudentDtoGet(item));
        }
        return studentsGet;
    }

    public StudentDtoGet get(Long id) {
        Student student = studentRepository.findById(id).get();
        if(student != null)
        {
            return new StudentDtoGet(student);
        }else{
            throw new ApiRequestException("Student Not Found");
        }
    }


}
