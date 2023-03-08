package com.keycloak.reactive.security

import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.JwtClaimNames.SUB
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import reactor.core.publisher.Mono

@Configuration
class JwtAuthConverter(
    private val jwtProps: JwtAuthConverterProperties
): Converter<Jwt, Mono<AbstractAuthenticationToken>> {

    private val jwtGrantedAuthoritiesConverter = JwtGrantedAuthoritiesConverter()

    override fun convert(jwt: Jwt): Mono<AbstractAuthenticationToken> {
        val authorities = jwtGrantedAuthoritiesConverter.getGrantAuthorities(jwt) + extractRoles(jwt)
        return Mono.create { JwtAuthenticationToken(jwt, authorities, jwt.principalName) }
    }

    private val Jwt.principalName get(): String {
        return jwtProps.principalAttribute?.let(this::getClaimAsString) ?: getClaimAsString(SUB)
    }

    private fun JwtGrantedAuthoritiesConverter.getGrantAuthorities(jwt: Jwt): Set<GrantedAuthority> {
        return convert(jwt)?.toSet() ?: setOf()
    }

    private fun extractRoles(jwt: Jwt): Set<GrantedAuthority> {
        val resourceAccess = jwt.getClaim<Map<String, Any>>(RESOURCE_ACCESS)
        val resource: Map<String, Any> = (resourceAccess[jwtProps.resourceId] as? Map<String, Any>) ?: mapOf()
        val resourceRoles: Collection<String> = (resource[ROLES] as? Collection<String>) ?: emptyList()
        return resourceRoles
            .map(::roleToGrantedAuthority)
            .toSet()
    }

    private fun roleToGrantedAuthority(role: String) =
        SimpleGrantedAuthority("ROLE_$role".uppercase())

    private companion object {
        const val RESOURCE_ACCESS = "resource_access"
        const val ROLES = "roles"
    }
}

