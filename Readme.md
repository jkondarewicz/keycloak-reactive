# Application

## Application is for testing purposes of keycloak with reactive spring (web flux).

# Running

1. Run dependents services by running command from root dir project `docker-compose up -d`\
2. Wait until all services are up and running `docker ps`
3. Run spring boot application by running gradle task `bootRun` or by your IDE

# URLs

1. Root application url: [keycloak reactive app](http://localhost:8081)
2. Keycloak application url: [keycloak app](http://localhost:8080)
3. Database view url: [view database](http://localhost:5000)
4. Database url: [database](http://localhost:27017)

# Development secrets

1. Keycloak administration access
    ```
    user:       keycloak_admin
    password:   keycloak_secret
    ```
2. Keycloak users
   ```
   Jakub Kondarewicz - ROLE_USER + ROLE_ADMIN
   user:     jakub.kondarewicz@test.com
   password: secret
   
   John Doe - ROLE_USER
   user:     john.doe@test.com
   password: secret
   ```
3. Database has no secrets
   
