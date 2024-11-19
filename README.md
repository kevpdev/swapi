

# SWAPI - The Star Wars API

Bienvenue dans **SWAPI**, une API Spring Boot conçue pour consommer et servir des données de l'univers de **Star Wars** en utilisant **Spring WebFlux** pour la programmation réactive. Cette API interagit avec une API distante (https://swapi.dev) pour récupérer des données liées aux films, personnages, planètes, espèces, vaisseaux spatiaux et véhicules de l'univers **Star Wars**.

## Table des matières

- [Installation](#installation)
- [Dépendances](#dépendances)
- [Fonctionnalités](#fonctionnalités)
- [Endpoints](#endpoints)
- [Exemples d'utilisation](#exemples-dutilisation)
- [Tests](#tests)
- [Swagger UI](#swagger-ui)
- [Contributions](#contributions)

## Installation

1. Clonez ce dépôt :
   ```bash
   git clone [<url_du_projet>](https://github.com/kevpdev/swapi)
   ```
2. Accédez au répertoire du projet :
   ```bash
   cd swapi
   ```
3. Compilez le projet avec Maven :
   ```bash
   mvn clean install
   ```
4. Lancez l'application :
   ```bash
   mvn spring-boot:run
   ```
   L'application sera accessible par défaut à l'URL **http://localhost:8080**.

## Dépendances

Voici les principales dépendances utilisées dans ce projet :

- **Java 21.**
- **Spring Boot 3.3.4** avec **Spring WebFlux** pour la programmation réactive.
- **Lombok** pour réduire le code standard.
- **SpringDoc OpenAPI** pour générer la documentation Swagger.
- **Reactor Test** pour les tests réactifs.
- **Spring Boot Starter Test** pour les tests unitaires et d'intégration.

### Fichier `pom.xml`

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-webflux</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>io.projectreactor</groupId>
        <artifactId>reactor-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webflux-ui</artifactId>
        <version>2.6.0</version>
    </dependency>
</dependencies>
```

## Fonctionnalités

L'API permet de :
- Récupérer les informations sur les **personnages** (People) de Star Wars.
- Obtenir les **films** et leurs détails.
- Récupérer les **vaisseaux spatiaux** (Starships) et **véhicules** (Vehicles).
- Accéder aux **espèces** et **planètes** de l'univers.
- Utiliser des hypermédias pour naviguer entre les entités liées.

## Endpoints

Les principaux endpoints incluent :

- **`GET /api/v1/people/{id}`** : Récupère les détails d'un personnage par son ID.
- **`GET /api/v1/films/{id}`** : Récupère les détails d'un film par son ID.
- **`GET /api/v1/starships/{id}`** : Récupère les détails d'un vaisseau spatial par son ID.
- **`GET /api/v1/vehicles/{id}`** : Récupère les détails d'un véhicule par son ID.
- **`GET /api/v1/species/{id}`** : Récupère les détails d'une espèce par son ID.
- **`GET /api/v1/planets/{id}`** : Récupère les détails d'une planète par son ID.

## Exemples d'utilisation

### Récupération d'un personnage par ID

```bash
GET http://localhost:8080/api/v1/people/1
```

Réponse :
```json
{
  "name": "Luke Skywalker",
  "height": "172",
  "mass": "77",
  "hair_color": "blond",
  ...
}
```

## Tests

Le projet inclut des tests unitaires et d'intégration utilisant **Spring Boot Starter Test** et **Reactor Test** pour les flux réactifs. Pour exécuter les tests :

```bash
mvn test
```

## Swagger UI

Pour visualiser et interagir avec la documentation de l'API, accédez à **Swagger UI** via l'URL suivante :

```bash
http://localhost:8080/swagger-ui/index.html
```

Vous pourrez y explorer les endpoints exposés et tester les requêtes directement à partir de l'interface.



Ce **README** présente votre projet **SWAPI** avec les informations essentielles pour son utilisation, son installation, et sa documentation API. N'hésitez pas à personnaliser davantage selon vos besoins spécifiques.
