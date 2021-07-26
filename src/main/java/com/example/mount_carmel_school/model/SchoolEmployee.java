package com.example.mount_carmel_school.model;

import com.example.mount_carmel_school.model.global.Auditable;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="school_employees")
public class SchoolEmployee extends Auditable<String> {

    @SequenceGenerator(
            name="school_employees_sequence",
            sequenceName = "school_employees_sequence"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "school_employees_sequence")
    private Long id;


    @NotNull
    @Column(nullable = false)
    private String residence;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="user_id",nullable = false)
    private User user;
}
