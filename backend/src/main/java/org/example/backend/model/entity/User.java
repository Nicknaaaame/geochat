package org.example.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String providerId;
    private String name;
    private String email;
    private String picture;
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserChats> chats = new ArrayList<>();
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
