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
@Table(name="contact_us_message_replies")
public class ContactUsMessageReply extends Auditable<String> {
    @SequenceGenerator(
            name = "contact_us_message_replies_sequence",
            sequenceName = "contact_us_message_replies_sequence"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "contact_us_message_replies_sequence")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="school_employee_id",referencedColumnName = "id")
    private SchoolEmployee schoolEmployee;

    @NotNull
    @ManyToOne
    @JoinColumn(name="contact_us_message_id",referencedColumnName = "id")
    private ContactUsMessage contactUsMessage;


    @NotNull
    @Column(nullable = false)
    private String replyMessage;

    @NotNull
    @Column(nullable = false)
    private String replyEmail;
}
