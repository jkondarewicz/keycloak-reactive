package com.keycloak.reactive.pets

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class PetController(
    private val petService: PetService
) {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/pets/{id}")
    fun getPetById(@PathVariable id: String): Mono<PetDTO> {
        return petService.getPetById(id)
    }

}