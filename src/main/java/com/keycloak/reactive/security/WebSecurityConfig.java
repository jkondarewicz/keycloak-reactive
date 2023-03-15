package com.keycloak.reactive.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class WebSecurityConfig {
    private final JwtAuthConverter jwtAuthConverter;
    @Bean
    SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        http
            .authorizeExchange()
            .anyExchange().authenticated();
        http
            .csrf().disable()
            .oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(jwtAuthConverter);
        return http.build();
    }

}