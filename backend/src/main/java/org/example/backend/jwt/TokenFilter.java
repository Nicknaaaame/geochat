package org.example.backend.jwt;

import org.example.backend.exception.JwtValidateException;
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

    private final TokenProvider tokenProvider;
    private final UserService userService;

    public TokenFilter(TokenProvider tokenProvider, UserService userService) {
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null)
            if (tokenProvider.validateToken(token)) {
                Authentication authentication = createAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else
                throw new JwtValidateException("Jwt not valid");
        filterChain.doFilter(request, response);
    }

    private Authentication createAuthentication(String token) {
        User user = userService.getUserById(tokenProvider.getId(token));
        UserPrincipal userPrincipal = new UserPrincipal(user, new HashMap<>());
        return new UsernamePasswordAuthenticationToken(
                userPrincipal,
                null,
                userPrincipal.getAuthorities());
    }
}
