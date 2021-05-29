package org.example.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(length = 2048)
    private String description;

    private String picture;

    @ManyToOne
    private User admin;
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "chat", cascade = CascadeType.REMOVE)
    private List<UserChats> users = new ArrayList<>();

    @OneToOne(cascade = CascadeType.REMOVE)
    private Location location;

    public Chat(Long id, String name, String description, String picture, User admin, Location location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.admin = admin;
        this.location = location;
    }

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "chat")
    private List<Message> messages;
}
