package org.example.backend.service.impl;

import org.example.backend.model.entity.Chat;
import org.example.backend.model.entity.Location;
import org.example.backend.service.ChatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Override
    public List<Chat> getChatsByLocation(Location location) {
        return null;
    }
}
