package org.example.backend.controller;

import org.example.backend.domain.User;
import org.example.backend.exception.UserNotFoundException;
import org.example.backend.security.UserPrincipal;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<?> getMe(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        User user = userService.getUserById(userPrincipal.getId()).orElseThrow(() ->
                new UserNotFoundException("User with id: " + userPrincipal.getId() + " not found"));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
