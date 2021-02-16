package org.example.backend.repository;

import org.example.backend.model.entity.PrivateChat;
import org.example.backend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PrivateChatRepository extends JpaRepository<PrivateChat, Long> {
    List<PrivateChat> findByUsersIn(Set<User> users);

    Optional<PrivateChat> findFirstByUsersIn(Set<User> users);
}
