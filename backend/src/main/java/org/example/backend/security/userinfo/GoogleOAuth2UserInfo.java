package org.example.backend.security.userinfo;

import java.util.Map;

public class GoogleOAuth2UserInfo extends AbstractOAuth2UserInfo {
    public GoogleOAuth2UserInfo(Map<String, Object> attrs) {
        super(attrs);
    }

    @Override
    public String getProviderId() {
        return (String) attrs.get("sub");
    }

    @Override
    public String getUsername() {
        return (String) attrs.get("name");
    }

    @Override
    public String getPictureUrl() {
        return attrs.get("picture").toString();
    }
}
