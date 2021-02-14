package org.example.backend.controller;

import org.example.backend.model.entity.User;
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
    private BlackListService service;

    @PostMapping("/block")
    public ResponseEntity<?> blockUser(@RequestPart Long userId) {
        service.blockUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/unblock")
    public ResponseEntity<?> unblockUser(@RequestPart Long userId) {
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
}
