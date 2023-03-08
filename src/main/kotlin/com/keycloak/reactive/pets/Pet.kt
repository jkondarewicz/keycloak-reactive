package com.keycloak.reactive.pets

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document
data class Pet(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val type: String
)