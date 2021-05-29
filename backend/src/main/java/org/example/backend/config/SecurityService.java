package org.example.backend.config;

import org.example.backend.service.ChatService;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    @Autowired
    private UserService userService;
    @Autowired
    private ChatService chatService;

    public boolean isAdmin(Long chatId) {
        return chatService.getChat(chatId).getAdmin().getId().equals(userService.getUser().getId());
    }
}
