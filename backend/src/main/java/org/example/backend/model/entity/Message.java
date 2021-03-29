package org.example.backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.model.entity.enums.MessageType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    @ManyToOne
    private User sender;

    @ManyToOne()
    private Chat chat;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    private LocalDateTime creationDate = LocalDateTime.now();

//    @OneToMany
//    private Set<Attachment> attachments;
}
