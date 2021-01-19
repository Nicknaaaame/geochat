package org.example.backend.model.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
public class Attachment extends AbstractEntity {
    private byte[] content;

    @ManyToOne
    private Message message;

    public Attachment(Long id, byte[] content, Message message) {
        super(id);
        this.content = content;
        this.message = message;
    }
}
