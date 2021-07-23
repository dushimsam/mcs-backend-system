package com.example.mount_carmel_school.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="school_news")
public class SchoolNews {

    @SequenceGenerator(
            name = "school_news_sequence",
            sequenceName = "school_news_sequence"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "school_news_sequence")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "school_employee_id",referencedColumnName = "id")
    private SchoolEmployee schoolEmployee;

    @NotNull
    private String title;

    @NotNull
    private String mainPicPath;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "schoolNews")
    private List<SchoolNewsParagraph> schoolNewsParagraphs;
}
