package org.example.backend.service.impl;

import org.example.backend.service.ChatService;
import org.example.backend.service.LocalChatService;
import org.example.backend.service.LocationService;
import org.example.backend.service.PrivateChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private LocalChatService localChatService;
    @Autowired
    private PrivateChatService privateChatService;

    @Override
    public List<Object> getUserChats() {
        List<Object> userChats = new ArrayList<>();
        userChats.addAll(privateChatService.getUserPrivateChats());
        userChats.addAll(localChatService.getUserLocalChats());
        return userChats;
    }
}
