package org.example.backend.jwt;

import org.example.backend.model.entity.User;
import org.example.backend.security.UserPrincipal;
import org.example.backend.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Component
public class TokenFilter extends OncePerRequestFilter {

    private final TokenStore tokenStore;
    private final UserService userService;

    public TokenFilter(TokenStore tokenStore, UserService userService) {
        this.tokenStore = tokenStore;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null && tokenStore.validateToken(token)) {
            Authentication authentication = createAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private Authentication createAuthentication(String token) {
        User user = userService.getUserById(tokenStore.getId(token));
        UserPrincipal userPrincipal = new UserPrincipal(user, new HashMap<>());
        return new UsernamePasswordAuthenticationToken(
                userPrincipal,
                null,
                userPrincipal.getAuthorities());
    }
}
