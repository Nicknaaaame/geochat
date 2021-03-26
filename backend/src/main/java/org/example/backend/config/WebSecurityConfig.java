package org.example.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.jwt.TokenFilter;
import org.example.backend.jwt.TokenProvider;
import org.example.backend.security.CustomOAuth2UserService;
import org.example.backend.security.CustomTokenResponseConverter;
import org.example.backend.security.InMemoryRequestRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final ObjectMapper mapper;
    private final TokenProvider tokenProvider;
    private final TokenFilter tokenFilter;
    private final CustomOAuth2UserService oAuth2UserService;

    public WebSecurityConfig(ObjectMapper mapper, TokenProvider tokenProvider,
                             TokenFilter tokenFilter, CustomOAuth2UserService oAuth2UserService) {
        this.mapper = mapper;
        this.tokenProvider = tokenProvider;
        this.tokenFilter = tokenFilter;
        this.oAuth2UserService = oAuth2UserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
                .csrf()
                    .disable()
                .cors()
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                    .antMatchers( "/oauth2/**", "/login**", "/ws/**", "/api/image/chat/**", "/api/image/user/**")
                        .permitAll()
                    .anyRequest()
                        .authenticated()
                        .and()
                .oauth2Login()
                    .authorizationEndpoint()
                        .baseUri("/login/oauth2/authorization")
                    .authorizationRequestRepository(new InMemoryRequestRepository())
                        .and()
                    .userInfoEndpoint()
                        .userService(oAuth2UserService)
                        .and()
                    .tokenEndpoint()
                        .accessTokenResponseClient(authorizationCodeTokenResponseClient())
                        .and()
                    .successHandler(this::successHandler)
//                    .failureHandler(this::failureHandler)
                        .and()
                .exceptionHandling()
                    .authenticationEntryPoint(this::authenticationEntryPoint)
                    .and()
                .logout()
                    .addLogoutHandler(this::logout)
                    .logoutSuccessHandler(this::onLogoutSuccess)
        ;
        //@formatter:on
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(myCorsFilter(), SessionManagementFilter.class);
    }

    @Bean
    MyCorsFilter myCorsFilter(){
        return new MyCorsFilter();
    }

    private OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> authorizationCodeTokenResponseClient() {
        OAuth2AccessTokenResponseHttpMessageConverter tokenResponseHttpMessageConverter
                = new OAuth2AccessTokenResponseHttpMessageConverter();

        tokenResponseHttpMessageConverter.setTokenResponseConverter(new CustomTokenResponseConverter());

        DefaultAuthorizationCodeTokenResponseClient tokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();

        RestTemplate restTemplate = new RestTemplate(Arrays.asList(new FormHttpMessageConverter(), tokenResponseHttpMessageConverter));

        restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());

        tokenResponseClient.setRestOperations(restTemplate);
        return tokenResponseClient;
    }

    private void logout(HttpServletRequest request, HttpServletResponse response,
                        Authentication authentication) {
        // You can process token here
        System.out.println("Auth token is - " + request.getHeader("Authorization"));
        SecurityContextHolder.clearContext();
    }

    void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                         Authentication authentication) throws IOException, ServletException {
        // this code is just sending the 200 ok response and preventing redirect
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedOrigins(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    private void failureHandler(HttpServletRequest request, HttpServletResponse response,
                                AuthenticationException exception) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> data = new HashMap<>();
        data.put("timestamp", Calendar.getInstance().getTime());
        System.out.println(exception.getMessage());
        data.put("exception", exception.getMessage());
        response.getOutputStream().println(objectMapper.writeValueAsString(data));
    }

    private void successHandler(HttpServletRequest request,
                                HttpServletResponse response, Authentication authentication) throws IOException {
        String collect = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(collect);
        String token = tokenProvider.generateToken(authentication);
        response.getWriter().write(
                mapper.writeValueAsString(Collections.singletonMap("accessToken", token))
        );
    }

    private void authenticationEntryPoint(HttpServletRequest request, HttpServletResponse response,
                                          AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(mapper.writeValueAsString(Collections.singletonMap("error", "Unauthenticated")));
        authException.printStackTrace();
    }
}
