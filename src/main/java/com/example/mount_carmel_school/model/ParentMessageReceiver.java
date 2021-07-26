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
@Table(name="parent_message_receivers")
public class ParentMessageReceiver extends Auditable<String> {

    @SequenceGenerator(
            name = "parent_message_receivers_sequence",
            sequenceName = "parent_message_receivers_sequence"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "parent_message_receivers_sequence")
    private Long id;

    @NotNull
    private Boolean isRead = false;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "parent_message_id",referencedColumnName = "id")
    private ParentMessage parentMessage;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User receiver;

}
