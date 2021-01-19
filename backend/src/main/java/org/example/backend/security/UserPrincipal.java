package org.example.backend.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.backend.model.entity.Role;
import org.example.backend.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Getter
@AllArgsConstructor
public class UserPrincipal implements OAuth2User {
    private Long id;
    private String name;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attrs;

    public UserPrincipal(User user, Map<String, Object> attrs) {
        this(user.getId(),
                user.getName(),
                user.getEmail(),
                Collections.singletonList(new SimpleGrantedAuthority(Role.ROLE_NONE.toString())),
                attrs);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attrs;
    }

    @Override
    public String getName() {
        return name;
    }
}
