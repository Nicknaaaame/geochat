package org.example.backend.repository;

import org.example.backend.model.entity.UserChats;
import org.example.backend.model.entity.UserChatsId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChatsRepository extends JpaRepository<UserChats, UserChatsId> {
}
