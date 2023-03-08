console.log('########################################Initializing database###############################################')
db = db.getSiblingDB('database');

console.log('Creating collections')
db.createCollection('Pet');

console.log("Inserting data into Pet collection");
const PetClass = "com.keycloak.reactive.pets.Pet";
const createPet = (id, name, type) => ({ _id: id, name, type, _class: PetClass });

db.Pet.insertMany([
    createPet('2e74344e-2534-4b72-be33-d7bef5e055bb', 'puszek', 'dog'),
    createPet('33dafaf0-4d5c-4474-a9f7-0193b04ed886', 'fifi', 'dog'),
    createPet('5d935ca9-9afe-47ba-8bfc-6370d3afaa76', 'mruczek', 'cat'),
    createPet('ffd530bd-b70e-4427-814b-2078a4fe8b61', 'nemo', 'fish')
]);

