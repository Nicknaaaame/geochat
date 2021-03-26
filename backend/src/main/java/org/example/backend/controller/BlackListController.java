package org.example.backend.controller;

import org.example.backend.model.entity.PrivateChat;
import org.example.backend.model.entity.User;
import org.example.backend.service.BlackListService;
import org.example.backend.service.PrivateChatService;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/blacklist-old")
public class BlackListController {
    @Autowired
    private BlackListService service;
    @Autowired
    private UserService userService;
    @Autowired
    private PrivateChatService privateChatService;

    @PostMapping("/block/{userId}")
    public ResponseEntity<?> blockUser(@PathVariable Long userId) {
        service.blockUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/unblock/{userId}")
    public ResponseEntity<?> unblockUser(@PathVariable Long userId) {
        service.unblockUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getBlackList() {
        return new ResponseEntity<>(service.getBlackList(), HttpStatus.OK);
    }

    @GetMapping("/blocked")
    public ResponseEntity<List<User>> getBlockedList() {
        return new ResponseEntity<>(service.getBlockedList(), HttpStatus.OK);
    }

    @GetMapping("/is-blocked/{id}")
    public ResponseEntity<Boolean> isUserBlocked(@PathVariable Long id) {
        return new ResponseEntity<>(service.isUserBlocked(userService.getUserById(id)), HttpStatus.OK);
    }

    @GetMapping("/is-in-blacklist/{id}")
    public ResponseEntity<Boolean> isUserInBlacklist(@PathVariable Long id) {
        return new ResponseEntity<>(service.isUserInBlackList(userService.getUserById(id)), HttpStatus.OK);
    }

    @GetMapping("/can-write/{chatId}")
    public ResponseEntity<Boolean> canWrite(@PathVariable Long chatId) {
        PrivateChat chat = privateChatService.getPrivateChat(chatId);
        return new ResponseEntity<>(service.isUserInBlackList(privateChatService.getOtherUser(chat)), HttpStatus.OK);
    }
}
