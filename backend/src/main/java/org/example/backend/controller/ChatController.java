package org.example.backend.controller;

import org.example.backend.model.entity.Chat;
import org.example.backend.model.request.SaveChatRequest;
import org.example.backend.service.ChatService;
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

    @GetMapping
    public ResponseEntity<List<Chat>> getChatsAround(){
        return new ResponseEntity<>(chatService.getChatsAround(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Chat> saveChat(@RequestBody SaveChatRequest request){
        return new ResponseEntity<>(chatService.saveChat(request), HttpStatus.OK);
    }
}
