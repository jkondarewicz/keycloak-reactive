package com.keycloak.reactive.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "jwt.auth.converter")
data class JwtAuthConverterProperties @ConstructorBinding constructor(
    val resourceId: String,
    val principalAttribute: String?
)
