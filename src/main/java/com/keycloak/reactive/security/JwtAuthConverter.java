package com.keycloak.reactive.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Configuration
class JwtAuthConverter implements Converter<Jwt, Mono<AbstractAuthenticationToken>> {

    private final JwtAuthConverterProperties jwtProps;

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Override
    public Mono<AbstractAuthenticationToken> convert(Jwt jwt) {
        var authorities = new HashSet<GrantedAuthority>();
        authorities.addAll(getGrantAuthorities(jwt));
        authorities.addAll(extractRoles(jwt));
        return Mono.just(new JwtAuthenticationToken(jwt, authorities, getPrincipalName(jwt)));
    }

    private String getPrincipalName(Jwt jwt) {
        return Optional
                .ofNullable(
                        jwt.getClaimAsString(jwtProps.getPrincipalAttribute())
                )
                .orElse(
                        jwt.getClaimAsString(JwtClaimNames.SUB)
                );
    }

    private Set<GrantedAuthority> getGrantAuthorities(Jwt jwt) {
        return new HashSet<>(
                jwtGrantedAuthoritiesConverter.convert(jwt)
        );
    }

    private Set<GrantedAuthority> extractRoles(Jwt jwt) {
        var resourceAccess = jwt.<Map<String, Object>>getClaim(RESOURCE_ACCESS);
        Map<String, Object> resources = new HashMap<String, Object>();
        try {
            resources = (Map<String, Object>) resourceAccess.get(jwtProps.getResourceId());
        } catch(Exception e) {}

        Collection<String> resourceRoles = new ArrayList<>();
        try {
            resourceRoles = (Collection<String>) resources.get(ROLES);
        } catch(Exception e) {}
        return resourceRoles
                .stream()
                .map(this::roleToGrantedAuthority)
                .collect(Collectors.toSet());
    }

    private SimpleGrantedAuthority roleToGrantedAuthority(String role) {
        return new SimpleGrantedAuthority("ROLE_" + role.toUpperCase());
    }

        private static final String RESOURCE_ACCESS = "resource_access";
        private static final String ROLES = "roles";
}

