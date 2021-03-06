package com.example.mount_carmel_school.dto.student_dto;


import com.example.mount_carmel_school.model.Student;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

@Data
public class StudentDtoGet {
        private Long id;
        private String studentCode;
        private String studentNames;
        private String studentClass;
        private String gender;
        private Date createdAt;
        private Date lastModifiedAt;
        public StudentDtoGet(Student student){
                BeanUtils.copyProperties(student,this);
        }
}
