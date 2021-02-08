package org.example.backend.repository;

import org.example.backend.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByLocalChatIdEquals(Long id);
    List<Message> findByPrivateChatIdEquals(Long id);
}
