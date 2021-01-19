package org.example.backend.model.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Message extends AbstractEntity {
    private String text;

    @ManyToOne
    private Chat chat;

    @ManyToOne
    private User sender;

    @OneToMany
    private Set<Attachment> attachments;
}
