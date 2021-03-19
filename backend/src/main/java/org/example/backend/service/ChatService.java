package org.example.backend.service;

import org.example.backend.model.entity.Chat;
import org.example.backend.model.request.SaveChatRequest;

import java.util.List;

public interface ChatService {
    List<Object> getUserChats();
    List<Chat> getChatsNearby();
    Chat saveChat(SaveChatRequest request);
    Chat getChat(Long id);
}
