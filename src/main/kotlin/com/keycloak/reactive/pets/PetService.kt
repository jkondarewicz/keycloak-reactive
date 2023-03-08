package com.keycloak.reactive.pets

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class PetService(
    private val petRepository: PetRepository
) {

    fun getPetById(id: String): Mono<PetDTO> {
        return petRepository
            .findById(id)
            .flatMap { Mono.just(PetDTO(it)) }
    }

}