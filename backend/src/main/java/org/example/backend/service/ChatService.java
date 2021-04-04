package org.example.backend.service;

import org.example.backend.model.entity.Chat;
import org.example.backend.model.entity.Message;
import org.example.backend.model.entity.User;
import org.example.backend.model.request.SaveChatRequest;

import java.util.List;

public interface ChatService {
    List<Chat> getUserChats();

    List<Chat> getChatsNearby();

    Chat saveChat(SaveChatRequest request);

    Chat saveChat(Chat chat);

    Chat getChat(Long id);

    Message joinChat(Long chatId);

    Message leaveChat(Long chatId);

    void deleteChat(Long chatId);

    Chat updateChat(Long chatId, SaveChatRequest request);

    void addUser(Chat chat, User user);

    void removeUser(Chat chat, User user);
}
