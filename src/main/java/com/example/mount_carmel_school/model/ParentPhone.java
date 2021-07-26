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
@Table(name="parent_phones")
public class ParentPhone extends Auditable<String> {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private String phone;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="parent_id",referencedColumnName = "id")
    private Parent parent;

}
