package com.keycloak.reactive.pets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "Pet")
public class Pet {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private String type;



}
