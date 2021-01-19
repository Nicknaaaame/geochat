package org.example.backend.security.userinfo;

import java.util.Map;

public class VkOAuth2UserInfo extends AbstractOAuth2UserInfo {
    public VkOAuth2UserInfo(Map<String, Object> attrs) {
        super(attrs);
    }

    @Override
    public String getProvidedUserId() {
        return (String) attrs.get("user_id");
    }

    @Override
    public String getUsername() {
        return (String) attrs.get("username");
    }

    @Override
    public String getPictureUrl() {
        return (String) attrs.get("picture");
    }
}
