package org.example.backend.repository;

import org.example.backend.model.entity.UserChats;
import org.example.backend.model.entity.UserChatsId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserChatsRepository extends JpaRepository<UserChats, UserChatsId> {
    Optional<UserChats> findByChatIdAndUserId(Long chatId, Long userId);
}
