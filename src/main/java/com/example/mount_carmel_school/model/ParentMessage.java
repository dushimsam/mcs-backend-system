package com.example.mount_carmel_school.model;

import com.example.mount_carmel_school.enums.MessageDirection;
import com.example.mount_carmel_school.enums.MessageStatus;
import com.example.mount_carmel_school.enums.MessageType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="parent_messages")
public class ParentMessage {

    @SequenceGenerator(
            name = "parent_messages_sequence",
            sequenceName = "parent_messages_sequence"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "parent_messages_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User sender;

    @NotNull
    private MessageType messageType;

    @NotNull
    private String message;

    @NotNull
    private MessageDirection messageDirection;

    @NotNull
    private MessageStatus messageStatus;

    @OneToMany(mappedBy = "parentMessage", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<ParentMessageReceiver> parentMessageReceiverList = new ArrayList<ParentMessageReceiver>();
}
