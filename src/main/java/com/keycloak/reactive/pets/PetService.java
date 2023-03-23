package com.keycloak.reactive.pets;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class PetService{
    @Autowired
    private final PetRepository petRepository;

    public Mono<PetDTO> getPetById(String id) {
        return petRepository
            .findById(id)
            .switchIfEmpty(Mono.error(new Exception("Pet not found")))
            .flatMap(pet -> Mono.just(PetDTO.fromPet(pet)));
    }

}