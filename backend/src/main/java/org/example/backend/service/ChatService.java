package org.example.backend.service;

import org.example.backend.model.entity.Chat;
import org.example.backend.model.entity.NewMessage;
import org.example.backend.model.request.SaveChatRequest;

import java.util.List;

public interface ChatService {
    List<Object> getUserChats();

    List<Chat> getChatsNearby();

    Chat saveChat(SaveChatRequest request);

    Chat saveChat(Chat chat);

    Chat getChat(Long id);

    NewMessage joinChat(Long chatId);

    NewMessage leaveChat(Long chatId);

    void deleteChat(Long chatId);

    Chat updateChat(Long chatId, SaveChatRequest request);
}
