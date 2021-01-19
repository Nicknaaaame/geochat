package org.example.backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "usr")
public class User extends AbstractEntity {
    private String providerId;
    private String name;
    private String email;
    private String picture;

    public User(Long id, String providerId, String name, String email, String picture) {
        super(id);
        this.providerId = providerId;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }
}
