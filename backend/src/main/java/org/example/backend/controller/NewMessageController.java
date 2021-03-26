package org.example.backend.controller;

import org.example.backend.converter.MessageDtoConverter;
import org.example.backend.exception.UserIsBlockedException;
import org.example.backend.model.entity.NewMessage;
import org.example.backend.model.request.SaveMessageRequest;
import org.example.backend.service.NewBlackListService;
import org.example.backend.service.NewMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
@CrossOrigin
public class NewMessageController {
    @Autowired
    private NewMessageService messageService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private NewBlackListService blackListService;

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<NewMessage>> getMessages(@PathVariable Long chatId) {
        return new ResponseEntity<>(messageService.getMessages(chatId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<NewMessage> saveMessage(@RequestBody SaveMessageRequest request) {
        if (!blackListService.isUserBlocked(request.getChatId())) {
            NewMessage message = messageService.saveMessage(request);
            simpMessagingTemplate.convertAndSend("/topic/message/chat/" + request.getChatId(), new MessageDtoConverter().apply(message));
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        throw new UserIsBlockedException();
    }
}
