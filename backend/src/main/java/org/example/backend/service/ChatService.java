package org.example.backend.service;

import org.example.backend.model.entity.Chat;
import org.example.backend.model.request.SaveChatRequest;

import java.util.List;

public interface ChatService {
    Chat saveChat(SaveChatRequest request);

    List<Chat> getChatsAround();
}
