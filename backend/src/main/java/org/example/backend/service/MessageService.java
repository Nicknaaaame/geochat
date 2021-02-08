package org.example.backend.service;

import org.example.backend.model.entity.Message;
import org.example.backend.model.request.SaveLocalMessageRequest;
import org.example.backend.model.request.SavePrivateMessageRequest;

import java.util.List;

public interface MessageService {
    Message saveMessage(SavePrivateMessageRequest request);
    Message saveMessage(SaveLocalMessageRequest request);
    Message saveMessage(Message message);
    List<Message> getPrivateMessages(Long chatId);
    List<Message> getLocalMessages(Long chatId);
}
