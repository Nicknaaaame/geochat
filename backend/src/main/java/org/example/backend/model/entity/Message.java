package org.example.backend.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.model.entity.enums.MessageType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @ManyToOne(optional = true)
    private LocalChat localChat;

    @ManyToOne(optional = true)
    private PrivateChat privateChat;

    @ManyToOne
    private User sender;

    private LocalDateTime creationDate = LocalDateTime.now();

    public Message(Long id, String text, MessageType messageType, LocalChat localChat, PrivateChat privateChat, User sender) {
        this.id = id;
        this.text = text;
        this.messageType = messageType;
        this.localChat = localChat;
        this.privateChat = privateChat;
        this.sender = sender;
    }

    //    @OneToMany
//    private Set<Attachment> attachments;
}
