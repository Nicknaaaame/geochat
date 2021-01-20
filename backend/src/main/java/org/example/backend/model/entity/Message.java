package org.example.backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.model.entity.enums.MessageType;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @ManyToOne
    private Chat chat;

    @ManyToOne
    private User sender;

//    @OneToMany
//    private Set<Attachment> attachments;
}
