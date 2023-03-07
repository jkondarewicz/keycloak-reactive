FROM quay.io/keycloak/keycloak:15.0.2 as builder

ENV KC_HEALTH_ENABLED=true
ENV KC_METRICS_ENABLED=true
ENV KC_FEATURES=token-exchange
ENV KC_DB=postgres
# Install custom providers
#RUN curl -sL https://github.com/aerogear/keycloak-metrics-spi/releases/download/2.5.3/keycloak-metrics-spi-2.5.3.jar -o /opt/keycloak/providers/keycloak-metrics-spi-2.5.3.jar
RUN ls -l /opt
RUN /opt/jboss/keycloak/bin/kc.sh build

FROM quay.io/keycloak/keycloak:15.0.2
ENV KC_DB=postgres
COPY --from=builder /opt/keycloak/ /opt/keycloak/
WORKDIR /opt/keycloak
# for demonstration purposes only, please make sure to use proper certificates in production instead
RUN keytool -genkeypair -storepass password -storetype PKCS12 -keyalg RSA -keysize 2048 -dname "CN=server" -alias server -ext "SAN:c=DNS:localhost,IP:127.0.0.1" -keystore conf/server.keystore

#COPY --chown=keycloak:root realm.json ./data/import/realm.json

HEALTHCHECK --interval=10s --timeout=3s \
    CMD curl -f http://localhost:8080/realms/master/.well-known/openid-configuration || exit 1
ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev", "--db=postgres" ]
#, "--import-realm"]