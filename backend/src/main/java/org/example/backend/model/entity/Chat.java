package org.example.backend.model.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Chat extends AbstractEntity {
    @ManyToOne
    private User admin;

    @ManyToMany
    private Set<User> users;

    @OneToOne
    private Location location;
}
