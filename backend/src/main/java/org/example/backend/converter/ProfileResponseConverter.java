package org.example.backend.converter;

import org.example.backend.model.entity.User;
import org.example.backend.model.response.ProfileResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProfileResponseConverter implements Function<User, ProfileResponse> {
    @Override
    public ProfileResponse apply(User user) {
        return ProfileResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .picture(user.getPicture())
                .showLocation(user.getShowLocation())
                .location(user.getLocation())
                .build();
    }
}
