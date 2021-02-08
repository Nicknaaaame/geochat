package org.example.backend.controller;

import org.example.backend.model.entity.LocalChat;
import org.example.backend.model.entity.PrivateChat;
import org.example.backend.model.request.JoinLocalChatRequest;
import org.example.backend.model.request.SaveLocalChatRequest;
import org.example.backend.model.request.SavePrivateChatRequest;
import org.example.backend.service.ChatService;
import org.example.backend.service.LocalChatService;
import org.example.backend.service.PrivateChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private LocalChatService localChatService;
    @Autowired
    private PrivateChatService privateChatService;


    @GetMapping("/around")
    public ResponseEntity<List<LocalChat>> getChatsAround() {
        return new ResponseEntity<>(localChatService.getLocalChatsAround(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Object>> getUserChats() {
        return new ResponseEntity<>(chatService.getUserChats(), HttpStatus.OK);
    }

    @PostMapping("/local")
    public ResponseEntity<LocalChat> saveLocalChat(@RequestBody SaveLocalChatRequest request) {
        return new ResponseEntity<>(localChatService.saveLocalChat(request), HttpStatus.OK);
    }

    @PostMapping("/local/join")
    public ResponseEntity<LocalChat> joinLocalChat(@RequestBody JoinLocalChatRequest request) {
        return new ResponseEntity<>(localChatService.joinLocalChat(request), HttpStatus.OK);
    }

    @PostMapping("/private")
    public ResponseEntity<PrivateChat> savePrivateChat(@RequestBody SavePrivateChatRequest request) {
        return new ResponseEntity<>(privateChatService.savePrivateChat(request), HttpStatus.OK);
    }
}
