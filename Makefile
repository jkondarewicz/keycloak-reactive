
REALM := SpringBootKeycloak

.PHONY: dependents.run
dependents.run: dependents.stop
	@docker-compose up -d

.PHONY: dependents.stop
dependents.stop:
	@docker-compose down
	@docker container prune --force
	@docker volume prune --force

.PHONY: keycloak.logs
keycloak.logs:
	@docker logs -f keycloak

.PHONY: keycloak.export
keycloak.export:
	@docker exec -it keycloak /opt/keycloak/bin/kc.sh \
		export \
		--file /opt/keycloak/data/import/realm.json \
		--realm ${REALM}
