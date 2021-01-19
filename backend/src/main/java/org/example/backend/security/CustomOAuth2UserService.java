package org.example.backend.security;

import org.example.backend.exception.OAuthProcessingException;
import org.example.backend.model.entity.User;
import org.example.backend.security.userinfo.AbstractOAuth2UserInfo;
import org.example.backend.security.userinfo.OAuth2UserInfoFactory;
import org.example.backend.security.userinfo.ProviderType;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequestEntityConverter;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        ProviderType providerType = ProviderType.getByName(registrationId);
        OAuth2User oAuth2User;
        if (providerType == ProviderType.VK) {
            oAuth2User = loadUserFromVk(userRequest);
        } else
            oAuth2User = super.loadUser(userRequest);
        AbstractOAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2User.getAttributes(),
                providerType);
        String email = oAuth2UserInfo.getEmail();
        if (email == null)
            throw new OAuthProcessingException("Email not found from OAuth2 " + registrationId + " provider");
        User user = userService.getUserByEmail(email).orElseGet(() -> userService.saveUserFrom(oAuth2UserInfo));
        return new UserPrincipal(user, oAuth2UserInfo.getResultAttrs());
    }

    private OAuth2User loadUserFromVk(OAuth2UserRequest userRequest) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("username", getUsername(userRequest));
        attributes.put("picture", getPictureUrl(userRequest));
        attributes.put("email", userRequest.getAdditionalParameters().get("email"));
        attributes.put("user_id", userRequest.getAdditionalParameters().get("user_id"));
        return new DefaultOAuth2User(Collections.singletonList(new SimpleGrantedAuthority("ROLE_NONE")), attributes, "username");
    }

    private String getPictureUrl(OAuth2UserRequest userRequest) {
        OAuth2UserRequest getPhotosRequest = createOAuth2UserRequestFrom(userRequest, "https://api.vk.com/method/photos.get?v=5.74&album_id=profile");
        //https://vk.com/images/camera_200.png?ava=1
        Map<String, Object> bodyFrom = extractBodyFrom(getPhotosRequest);
        LinkedHashMap<String, Object> body = (LinkedHashMap<String, Object>) bodyFrom.get("response");
        if ((Integer) body.get("count") == 0)
            return "https://vk.com/images/camera_200.png?ava=1";
        ArrayList<LinkedHashMap<String, Object>> photos = (ArrayList<LinkedHashMap<String, Object>>) body.get("items");
        return (String) photos.get(0).get("url");
    }

    public String getUsername(OAuth2UserRequest userRequest) {
        OAuth2UserRequest getUsernameRequest = createOAuth2UserRequestFrom(userRequest, "https://api.vk.com/method/users.get?v=5.74");
        Map<String, Object> bodyFrom = extractBodyFrom(getUsernameRequest);
        ArrayList<LinkedHashMap<String, Object>> response = (ArrayList<LinkedHashMap<String, Object>>) bodyFrom.get("response");
        Map<String, Object> body = (Map<String, Object>) response.get(0);
        String firstName = (String) body.get("first_name");
        String lastName = (String) body.get("last_name");
        return firstName + " " + lastName;
    }

    private Map<String, Object> extractBodyFrom(OAuth2UserRequest userRequest) {
        Converter<OAuth2UserRequest, RequestEntity<?>> requestEntityConverter = new OAuth2UserRequestEntityConverter();
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<?> convert = requestEntityConverter.convert(userRequest);
        ResponseEntity<Map<String, Object>> exchange = restTemplate.exchange(convert, new ParameterizedTypeReference<Map<String, Object>>() {
        });
        return exchange.getBody();
    }

    private OAuth2UserRequest createOAuth2UserRequestFrom(OAuth2UserRequest userRequest, String userInfoUri) {
        ClientRegistration registration = userRequest.getClientRegistration();
        ClientRegistration clientRegistration = ClientRegistration
                .withRegistrationId(registration.getRegistrationId())
                .clientId(registration.getClientId())
                .clientSecret(registration.getClientSecret())
                .clientAuthenticationMethod(registration.getClientAuthenticationMethod())
                .authorizationGrantType(registration.getAuthorizationGrantType())
                .redirectUri(registration.getRedirectUri())
                .scope(registration.getScopes())
                .authorizationUri(registration.getProviderDetails().getAuthorizationUri())
                .tokenUri(registration.getProviderDetails().getTokenUri())
                .userInfoUri(userInfoUri)
                .build();
        return new OAuth2UserRequest(clientRegistration, userRequest.getAccessToken());
    }
}
