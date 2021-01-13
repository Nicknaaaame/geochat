package org.example.backend.security;

import org.example.backend.domain.User;
import org.example.backend.exception.OAuthProcessingException;
import org.example.backend.security.userinfo.AbstractOAuth2UserInfo;
import org.example.backend.security.userinfo.OAuth2UserInfoFactory;
import org.example.backend.security.userinfo.ProviderType;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        AbstractOAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2User.getAttributes(),
                ProviderType.getByName(registrationId));
        String email = oAuth2UserInfo.getEmail();
        if (email == null)
            throw new OAuthProcessingException("Email not found from OAuth2 " + registrationId + " provider");
        User user = userService.getUserByEmail(email).orElseGet(() -> userService.saveUserFrom(oAuth2UserInfo));
        return new UserPrincipal(user, oAuth2UserInfo.getResultAttrs());
    }
}
