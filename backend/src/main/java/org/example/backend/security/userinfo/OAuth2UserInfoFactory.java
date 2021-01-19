package org.example.backend.security.userinfo;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static AbstractOAuth2UserInfo getOAuth2UserInfo(Map<String, Object> attrs, ProviderType providerType) {
        switch (providerType) {
            case GOOGLE:
                return new GoogleOAuth2UserInfo(attrs);
            case VK:
                return new VkOAuth2UserInfo(attrs);
        }
        throw new IllegalArgumentException(providerType.toString());
    }
}
