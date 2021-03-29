package org.example.backend.service;

import org.example.backend.model.entity.Message;
import org.example.backend.model.request.SaveMessageRequest;

import java.util.List;

public interface MessageService {
    Message saveMessage(SaveMessageRequest request);
    Message saveMessage(Message message);
    List<Message> getMessages(Long chatId);
}
