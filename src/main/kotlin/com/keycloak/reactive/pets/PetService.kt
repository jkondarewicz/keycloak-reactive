package com.keycloak.reactive.pets

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Service
class PetService(
    private val petRepository: PetRepository
) {

    fun getPetById(id: String): Mono<PetDTO> {
        return petRepository
            .findById(id)
            .switchIfEmpty { throw Exception("Not found") }
            .flatMap { Mono.just(PetDTO(it)) }

    }

}