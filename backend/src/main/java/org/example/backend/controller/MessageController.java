package org.example.backend.controller;

import org.example.backend.model.entity.Message;
import org.example.backend.model.request.SaveLocalMessageRequest;
import org.example.backend.model.request.SavePrivateMessageRequest;
import org.example.backend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
@CrossOrigin
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/private/{chatId}")
    public ResponseEntity<List<Message>> getPrivateMessages(@PathVariable Long chatId) {
        return new ResponseEntity<>(messageService.getPrivateMessages(chatId), HttpStatus.OK);
    }

    @GetMapping("/local/{chatId}")
    public ResponseEntity<List<Message>> getLocalMessages(@PathVariable Long chatId) {
        return new ResponseEntity<>(messageService.getLocalMessages(chatId), HttpStatus.OK);
    }

    @PostMapping("/private")
    public ResponseEntity<Message> savePrivateMessage(@RequestBody SavePrivateMessageRequest request) {
        Message message = messageService.saveMessage(request);
        simpMessagingTemplate.convertAndSend("/topic/message/private/" + request.getChatId(), message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/local")
    public ResponseEntity<Message> saveLocalMessage(@RequestBody SaveLocalMessageRequest request) {
        Message message = messageService.saveMessage(request);
        simpMessagingTemplate.convertAndSend("/topic/message/local/" + request.getChatId(), message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
