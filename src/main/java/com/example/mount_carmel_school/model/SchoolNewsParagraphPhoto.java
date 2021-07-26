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
@Table(name="school_news_paragraphs_photos")
public class SchoolNewsParagraphPhoto extends Auditable<String> {
    @SequenceGenerator(
            name = "school_news_paragraphs_photos",
            sequenceName = "school_news_paragraphs_photos"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "school_news_paragraphs_photos")
    private Long id;


    @NotNull
    @Column(nullable = false)
    private String photoPath;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "school_news_paragraph_id",referencedColumnName = "id")
    private SchoolNewsParagraph schoolNewsParagraph;
}
