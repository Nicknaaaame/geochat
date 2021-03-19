package org.example.backend.repository;

import org.example.backend.model.entity.NewMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewMessageRepository extends JpaRepository<NewMessage, Long> {
    List<NewMessage> findByChatIdEquals(Long chatId);
}
