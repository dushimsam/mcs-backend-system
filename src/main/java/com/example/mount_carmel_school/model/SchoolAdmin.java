package com.example.mount_carmel_school.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="school_admins")
public class SchoolAdmin {
    @SequenceGenerator(
            name = "school_admins_sequence",
            sequenceName = "school_admins_sequence"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "school_admins_sequence")
    private Long id;

    @NotNull
    private String residence;

    @NotNull
    @OneToOne
    private User user;

}
