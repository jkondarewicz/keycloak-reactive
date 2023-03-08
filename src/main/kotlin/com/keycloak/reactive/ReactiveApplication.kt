package com.keycloak.reactive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import org.springframework.web.reactive.config.EnableWebFlux

@ConfigurationPropertiesScan
@EnableWebFlux
@EnableReactiveMongoRepositories
@SpringBootApplication
class ReactiveApplication

fun main(args: Array<String>) {
	runApplication<ReactiveApplication>(*args)
}
