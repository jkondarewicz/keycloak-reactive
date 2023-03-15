package com.keycloak.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@ConfigurationPropertiesScan
@EnableWebFlux
@EnableReactiveMongoRepositories
@SpringBootApplication
class ReactiveApplication {
	public static void main(String args[]) {
		SpringApplication.run(ReactiveApplication.class, args);
	}
}
