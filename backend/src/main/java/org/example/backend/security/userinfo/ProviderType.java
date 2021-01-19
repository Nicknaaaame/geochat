package org.example.backend.security.userinfo;

import org.example.backend.exception.OAuthProcessingException;

public enum ProviderType {
    GOOGLE("google"),
    VK("vk");
    private final String name;

    ProviderType(String name) {
        this.name = name;
    }

    public static ProviderType getByName(String name) {
        for (ProviderType providerType : ProviderType.values())
            if (providerType.name.equals(name))
                return providerType;
        throw new OAuthProcessingException("Provider with name: " + name + " is not supported");
    }
}
