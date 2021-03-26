package org.example.backend.controller;

import org.example.backend.converter.MessageDtoConverter;
import org.example.backend.exception.UserIsBlockedException;
import org.example.backend.model.entity.Chat;
import org.example.backend.model.entity.LocalChat;
import org.example.backend.model.entity.NewMessage;
import org.example.backend.model.entity.PrivateChat;
import org.example.backend.model.request.*;
import org.example.backend.service.ChatService;
import org.example.backend.service.LocalChatService;
import org.example.backend.service.NewBlackListService;
import org.example.backend.service.PrivateChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private LocalChatService localChatService;
    @Autowired
    private PrivateChatService privateChatService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private NewBlackListService blackListService;

    @GetMapping("/nearby")
    public ResponseEntity<List<Chat>> getChatsNearby() {
        return new ResponseEntity<>(chatService.getChatsNearby(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Chat> saveChat(@ModelAttribute SaveChatRequest request) {
        return new ResponseEntity<>(chatService.saveChat(request), HttpStatus.OK);
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<Chat> getChat(@PathVariable Long chatId) {
        return new ResponseEntity<>(chatService.getChat(chatId), HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<Void> joinChat(@RequestBody ChatIdRequest request) {
        if (!blackListService.isUserBlocked(request.getChatId())) {
            NewMessage joinMessage = chatService.joinChat(request.getChatId());
            simpMessagingTemplate.convertAndSend("/topic/message/chat/" + request.getChatId(), new MessageDtoConverter().apply(joinMessage));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new UserIsBlockedException();
    }

    @PostMapping("/leave")
    public ResponseEntity<Void> leaveChat(@RequestBody ChatIdRequest request) {
        NewMessage joinMessage = chatService.leaveChat(request.getChatId());
        simpMessagingTemplate.convertAndSend("/topic/message/chat/" + request.getChatId(), new MessageDtoConverter().apply(joinMessage));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{chatId}")
    public ResponseEntity<Void> deleteChat(@PathVariable Long chatId) {
        chatService.deleteChat(chatId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{chatId}")
    public ResponseEntity<Chat> updateChat(@PathVariable Long chatId, @ModelAttribute SaveChatRequest request) {
        return new ResponseEntity<>(chatService.updateChat(chatId, request), HttpStatus.OK);
    }

    //remove below
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
        PrivateChat privateChat = privateChatService.savePrivateChat(request);
        simpMessagingTemplate.convertAndSend("/topic/private/" + request.getUserId(), privateChat);
        return new ResponseEntity<>(privateChat, HttpStatus.OK);
    }
}
