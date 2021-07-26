package com.example.mount_carmel_school.model;


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
@Table(name="contact_us_messages")
public class ContactUsMessage extends Auditable<String> {

    @SequenceGenerator(
            name = "contact_us_messages_sequence",
            sequenceName = "contact_us_messages_sequence"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "contact_us_messages_sequence")
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String names;

    @NotNull
    @Column(nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String message;

    @NotNull
    @Column(nullable = false)
    private boolean isRead = false;

    @NotNull
    @Column(nullable = false)
    private boolean isReplied = false;

    @JsonIgnore
    @OneToMany(mappedBy = "contactUsMessage")
    private List<ContactUsMessageReply> contactUsMessageReplies;
}
