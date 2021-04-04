package org.example.backend.controller;

import org.example.backend.converter.ChatResponseConverter;
import org.example.backend.converter.MessageDtoConverter;
import org.example.backend.exception.UserIsBlockedException;
import org.example.backend.model.entity.Message;
import org.example.backend.model.request.ChatIdRequest;
import org.example.backend.model.request.SaveChatRequest;
import org.example.backend.model.response.ChatResponse;
import org.example.backend.service.BlackListService;
import org.example.backend.service.ChatService;
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
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private BlackListService blackListService;
    @Autowired
    private ChatResponseConverter chatResponseConverter;

    @GetMapping("/nearby")
    public ResponseEntity<List<ChatResponse>> getChatsNearby() {
        return new ResponseEntity<>(chatResponseConverter.toList(chatService.getChatsNearby()), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<ChatResponse>> getUserChats() {
        return new ResponseEntity<>(chatResponseConverter.toList(chatService.getUserChats()), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ChatResponse> saveChat(@ModelAttribute SaveChatRequest request) {
        return new ResponseEntity<>(chatResponseConverter.apply(chatService.saveChat(request)), HttpStatus.OK);
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<ChatResponse> getChat(@PathVariable Long chatId) {
        return new ResponseEntity<>(chatResponseConverter.apply(chatService.getChat(chatId)), HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<Void> joinChat(@RequestBody ChatIdRequest request) {
        if (!blackListService.isUserBlocked(request.getChatId())) {
            Message joinMessage = chatService.joinChat(request.getChatId());
            simpMessagingTemplate.convertAndSend("/topic/message/chat/" + request.getChatId(), new MessageDtoConverter().apply(joinMessage));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new UserIsBlockedException();
    }

    @PostMapping("/leave")
    public ResponseEntity<Void> leaveChat(@RequestBody ChatIdRequest request) {
        Message joinMessage = chatService.leaveChat(request.getChatId());
        simpMessagingTemplate.convertAndSend("/topic/message/chat/" + request.getChatId(), new MessageDtoConverter().apply(joinMessage));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{chatId}")
    public ResponseEntity<Void> deleteChat(@PathVariable Long chatId) {
        chatService.deleteChat(chatId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{chatId}")
    public ResponseEntity<ChatResponse> updateChat(@PathVariable Long chatId, @ModelAttribute SaveChatRequest request) {
        return new ResponseEntity<>(chatResponseConverter.apply(chatService.updateChat(chatId, request)), HttpStatus.OK);
    }
}
