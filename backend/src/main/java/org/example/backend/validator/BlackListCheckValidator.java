package org.example.backend.validator;

import org.example.backend.model.entity.PrivateChat;
import org.example.backend.model.entity.User;
import org.example.backend.service.BlackListService;
import org.example.backend.service.PrivateChatService;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BlackListCheckValidator implements ConstraintValidator<BlackListCheck, Long> {
    @Autowired
    private BlackListService blackListService;
    @Autowired
    private PrivateChatService privateChatService;
    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        PrivateChat chat = privateChatService.getPrivateChat(value);
        User currUser = userService.getUser();
        User toUser = chat.getUsers().stream().filter(user -> !user.getId().equals(currUser.getId())).findFirst().orElseThrow();
        return !blackListService.isUserBlocked(toUser)
                && !blackListService.isUserInBlackList(toUser);
    }
}
