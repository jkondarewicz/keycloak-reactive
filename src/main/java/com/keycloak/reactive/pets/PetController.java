package com.keycloak.reactive.pets;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class PetController {
    @Autowired
    private final PetService petService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/pets/{id}")
    Mono<PetDTO> getPetById(@PathVariable String id) {
        return petService.getPetById(id);
    }

}