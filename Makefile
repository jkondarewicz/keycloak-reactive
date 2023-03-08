
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

keycloak.export:
	@docker exec -it keycloak /opt/jboss/keycloak/bin/standalone.sh \
		-Djboss.socket.binding.port-offset=100 \
		-Dkeycloak.migration.action=export \
		-Dkeycloak.migration.provider=singleFile \
		-Dkeycloak.migration.realmName=${REALM} \
		-Dkeycloak.migration.usersExportStrategy=REALM_FILE \
		-Dkeycloak.migration.file=/opt/exported_realm/realm.json

keycloak.import:
	@docker exec -it keycloak /opt/jboss/keycloak/bin/standalone.sh \
		-Djboss.socket.binding.port-offset=100 \
		-Dkeycloak.migration.action=import \
		-Dkeycloak.migration.provider=singleFile \
		-Dkeycloak.migration.realmName=${REALM} \
		-Dkeycloak.migration.usersExportStrategy=REALM_FILE \
		-Dkeycloak.migration.file=/opt/exported_realm/realm.json
	@docker restart keycloak
