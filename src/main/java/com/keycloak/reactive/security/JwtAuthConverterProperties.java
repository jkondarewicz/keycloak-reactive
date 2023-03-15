package com.keycloak.reactive.security;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Getter
@ConfigurationProperties(prefix = "jwt.auth.converter")
public class JwtAuthConverterProperties {
    private final String resourceId;
    private final String principalAttribute;

    @ConstructorBinding
    public JwtAuthConverterProperties(
            String resourceId,
            String principalAttribute
    ) {
        this.resourceId = resourceId;
        this.principalAttribute = principalAttribute;
    }
}
