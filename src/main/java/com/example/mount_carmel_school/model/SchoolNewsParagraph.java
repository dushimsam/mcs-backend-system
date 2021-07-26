package com.example.mount_carmel_school.model;


import com.example.mount_carmel_school.enums.ParagraphPhotoSort;
import com.example.mount_carmel_school.model.global.Auditable;
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
@Table(name="school_news_paragraphs")
public class SchoolNewsParagraph extends Auditable<String> {
    @SequenceGenerator(
            name = "school_news_sequence",
            sequenceName = "school_news_sequence"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "school_news_sequence")
    private Long id;


    @NotNull
    @Column(nullable = false)
    private String paragraph;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ParagraphPhotoSort paragraphPhotoSort = ParagraphPhotoSort.PIC_LAST;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "school_news_id",referencedColumnName = "id")
    private SchoolNews schoolNews;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "schoolNewsParagraph")
    private List<SchoolNewsParagraphPhoto> schoolNewsParagraphPhotos;
}
