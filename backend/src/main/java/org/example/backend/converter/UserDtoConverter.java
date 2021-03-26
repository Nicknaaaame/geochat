package org.example.backend.converter;

import org.example.backend.model.dto.UserDto;
import org.example.backend.model.entity.User;

import java.util.function.Function;

public class UserDtoConverter implements Function<User, UserDto> {
    @Override
    public UserDto apply(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .picture(user.getPicture())
                .build();
    }
}
