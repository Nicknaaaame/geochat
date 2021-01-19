package org.example.backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usr")
//TODO add field "isOnline"
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String providerId;
    private String name;
    private String email;
    private String picture;
    private Boolean showLocation = false;

    @OneToOne
    private Location location;

    public User(Long id, String providerId, String name, String email, String picture) {
        this.id = id;
        this.providerId = providerId;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }
}
