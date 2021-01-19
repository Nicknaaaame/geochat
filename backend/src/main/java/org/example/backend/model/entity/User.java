package org.example.backend.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "usr")
public class User extends AbstractEntity {
    private String providerId;
    private String name;
    private String email;
    private String picture;

    @Column(nullable = true)
    private Boolean showLocation = false;

    @OneToOne
    @Column(nullable = true)
    private Location location;

    public User(Long id, String providerId, String name, String email, String picture) {
        super(id);
        this.providerId = providerId;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }
}
