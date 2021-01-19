package org.example.backend.security.userinfo;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractOAuth2UserInfo {
    protected Map<String, Object> attrs;
    protected Map<String, Object> resultAttrs;


    public AbstractOAuth2UserInfo(Map<String, Object> attrs) {
        this.attrs = attrs;
        resultAttrs = new HashMap<>();
        resultAttrs.put("id", getProvidedUserId());
        resultAttrs.put("name", getUsername());
    }

    public abstract String getProvidedUserId();

    public abstract String getUsername();

    public String getEmail() {
        return (String) attrs.get("email");
    }

    public abstract String getPictureUrl();

    public Map<String, Object> getResultAttrs() {
        return resultAttrs;
    }
}
