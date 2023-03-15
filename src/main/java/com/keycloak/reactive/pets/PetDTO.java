package com.keycloak.reactive.pets;

import org.springframework.data.annotation.Id;
import java.util.*;

public record PetDTO(
    String id,
    String name,
    String type
) {

    public static PetDTO fromPet(Pet pet) {
        return new PetDTO(pet.getId(), pet.getName(), pet.getType());
    }
}