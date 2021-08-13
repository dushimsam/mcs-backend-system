package com.example.mount_carmel_school.model.global;

import com.example.mount_carmel_school.model.SchoolNews;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="school_news_photos")
public class SchoolNewsPhoto  extends Auditable<String>{

    @SequenceGenerator(
            name="school_news_photos_sequence",
            sequenceName = "school_news_photos_sequence"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "school_news_photos_sequence")
    private Long id;


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_news_id",referencedColumnName = "id")
    private SchoolNews schoolNews;

    @NotNull
    private String url;
}
