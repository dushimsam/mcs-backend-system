package com.example.mount_carmel_school.model;
import com.example.mount_carmel_school.model.global.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="students")
public class Student extends Auditable<String> {
    @SequenceGenerator(
            name = "students_sequence",
            sequenceName = "students_sequence"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "students_sequence")
    private Long id;

    @NotNull
    @Column(unique = true)
    private String studentCode;

    @NotNull
    private String studentNames;

    @NotNull
    private String gender;

    @NotNull
    @Column(nullable = false)
    private String studentClass;

}
