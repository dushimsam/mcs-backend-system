package com.example.mount_carmel_school.dto.parent_dto;

import com.example.mount_carmel_school.dto.UserDto.UserDtoGet;
import com.example.mount_carmel_school.dto.parent_phone.ParentPhoneDtoGet;
import com.example.mount_carmel_school.dto.student_dto.StudentDtoGet;
import com.example.mount_carmel_school.model.Parent;
import com.example.mount_carmel_school.model.ParentPhone;

import com.example.mount_carmel_school.model.Student;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ParentDtoGet {
    private Long id;
    private String residence;
    private UserDtoGet user;
    private List<StudentDtoGet> students = new ArrayList<>();
    private List<ParentPhoneDtoGet> phones = new ArrayList<>();
    private Date createdAt;
    private Date lastModifiedAt;
    public ParentDtoGet(Parent parent){
        BeanUtils.copyProperties(parent,this,"user");
        this.user = new UserDtoGet(parent.getUser());

        if(parent.getPhones() != null)
        {
            for(ParentPhone parentPhone: parent.getPhones())
            {
                this.phones.add(new ParentPhoneDtoGet(parentPhone,"PARENT"));
            }

        }

        if(parent.getStudents() != null)
        {
            for(Student student: parent.getStudents())
            {
                this.students.add(new StudentDtoGet(student));
            }

        }
    }

    public ParentDtoGet(Parent parent,String ignore){
        BeanUtils.copyProperties(parent,this,"user");
        this.user = new UserDtoGet(parent.getUser());


        if(parent.getStudents() != null)
        {
            for(Student student: parent.getStudents())
            {
                this.students.add(new StudentDtoGet(student));
            }

        }

        if(parent.getPhones() != null && !ignore.equals("PHONE"))
        {
            for(ParentPhone parentPhone: parent.getPhones())
            {
                this.phones.add(new ParentPhoneDtoGet(parentPhone,"PARENT"));
            }

        }

    }
}
