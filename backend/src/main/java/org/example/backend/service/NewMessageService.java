package org.example.backend.service;

import org.example.backend.model.entity.NewMessage;
import org.example.backend.model.request.SaveMessageRequest;

import java.util.List;

public interface NewMessageService {
    NewMessage saveMessage(SaveMessageRequest request);
    List<NewMessage> getMessages(Long chatId);
}
