package com.keycloak.reactive.pets

import org.springframework.data.annotation.Id
import java.util.*

data class PetDTO(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val type: String
) {
    companion object {
        operator fun invoke(pet: Pet) =
            PetDTO(pet.id, pet.name, pet.type)
    }
}