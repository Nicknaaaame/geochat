package org.example.backend.security;

import lombok.Getter;
import org.example.backend.domain.Role;
import org.example.backend.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Getter
public class UserPrincipal implements OAuth2User {
    private Long id;
    private String name;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attrs;

    public UserPrincipal(User user, Map<String, Object> attrs) {
        this(user.getId(),
                user.getName(),
                Collections.singletonList(new SimpleGrantedAuthority(Role.ROLE_NONE.toString())),
                attrs);
    }

    public UserPrincipal(Long id, String name, Collection<? extends GrantedAuthority> authorities, Map<String, Object> attrs) {
        this.id = id;
        this.name = name;
        this.authorities = authorities;
        this.attrs = attrs;
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
