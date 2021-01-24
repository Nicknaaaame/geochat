package org.example.backend.service;

import org.example.backend.model.entity.PrivateChat;
import org.example.backend.model.request.SavePrivateChatRequest;

import java.util.List;

public interface PrivateChatService {
    PrivateChat savePrivateChat(SavePrivateChatRequest request);

    List<PrivateChat> getUserPrivateChats();
}
