package com.example.mount_carmel_school.service;
import com.example.mount_carmel_school.dto.DeleteResponseDto;
import com.example.mount_carmel_school.dto.student_dto.StudentDtoGet;
import com.example.mount_carmel_school.dto.student_dto.StudentDtoPost;
import com.example.mount_carmel_school.exception.ApiRequestException;
import com.example.mount_carmel_school.exception.NotFoundException;
import com.example.mount_carmel_school.model.Parent;
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

    @Autowired
    private ParentService parentService;

    public StudentDtoGet add(StudentDtoPost studentDtoPost)  {
        Parent parent = parentRepository.findById(studentDtoPost.getParentId()).orElseThrow(()-> new NotFoundException("Parent"));
        Student newStudent = new Student();
        BeanUtils.copyProperties(studentDtoPost, newStudent);
        Student savedStudent = studentRepository.save(newStudent);
        parentService.addStudentToParent(parent.getId(),savedStudent.getId());
        return  new StudentDtoGet(savedStudent);
    }


    public StudentDtoGet update(Long studentId,StudentDtoPost studentDtoPost)  {
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new NotFoundException("Student"));
        BeanUtils.copyProperties(studentDtoPost, student);
        Student savedStudent = studentRepository.save(student);
        return  new StudentDtoGet(savedStudent);
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
        Student student = studentRepository.findById(id).orElseThrow(()->new NotFoundException("Student"));
        return new StudentDtoGet(student);
    }


    public DeleteResponseDto delete(Long id) {
          Student student = studentRepository.findById(id).orElseThrow(()->new NotFoundException("Student"));
          studentRepository.delete(student);
        return new DeleteResponseDto(student);
    }

}
