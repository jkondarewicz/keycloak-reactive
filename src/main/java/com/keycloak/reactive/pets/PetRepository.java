package com.keycloak.reactive.pets;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

interface PetRepository extends ReactiveMongoRepository<Pet, String> { }