package org.example.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

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

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "users")
    private Set<Chat> chats;

    public User(Long id, String providerId, String name, String email, String picture) {
        this.id = id;
        this.providerId = providerId;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }
}
