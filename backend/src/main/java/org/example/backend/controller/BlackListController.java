package org.example.backend.controller;

import org.example.backend.model.entity.User;
import org.example.backend.model.request.BlackListRequest;
import org.example.backend.service.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/blacklist")
public class BlackListController {
    @Autowired
    private BlackListService blackListService;

    @PostMapping("/block")
    public ResponseEntity<Void> blockUser(@RequestBody BlackListRequest request) {
        blackListService.blockUser(request.getUserId(), request.getChatId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/unblock")
    public ResponseEntity<Void> unblockUser(@RequestBody BlackListRequest request) {
        blackListService.unblockUser(request.getUserId(), request.getChatId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<User>> getBlackList(@PathVariable Long chatId) {
        return new ResponseEntity<>(blackListService.getBlackList(chatId), HttpStatus.OK);
    }
}
