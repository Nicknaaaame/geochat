package org.example.backend.service;

import org.example.backend.model.entity.LocalChat;
import org.example.backend.model.request.JoinLocalChatRequest;
import org.example.backend.model.request.SaveLocalChatRequest;

import java.util.List;

public interface LocalChatService {
    LocalChat saveLocalChat(SaveLocalChatRequest request);

    LocalChat saveLocalChat(LocalChat localChat);

    LocalChat joinLocalChat(JoinLocalChatRequest request);

    LocalChat getLocalChat(Long id);

    List<LocalChat> getLocalChatsAround();

    List<LocalChat> getUserLocalChats();
}
