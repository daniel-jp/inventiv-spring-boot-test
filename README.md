

**RÉALISATION DU BACKEND DU MINI-PROJET SPRING BOOT :**

        1. **Objectif atteint :**     

        J'ai créé un service Web RESTful en utilisant Spring Boot, avec les quatre fonctionnalités CRUD (Create, Read, Update, Delete) mises en œuvre comme demandé.

        2. **Endpoints corrects :** 

        J'ai correctement implémenté les endpoints pour chaque fonctionnalité, en utilisant les méthodes HTTP appropriées (GET, PUT, POST, DELETE) et en respectant les URI spécifiées.

        3. **Format des données :** 

        J'ai assuré que les données envoyées et reçues sont au format JSON, conformément aux exigences. J'ai effectué des tests avec Postman pour valider cette fonctionnalité.

        4. **Sécurité :** 

        En plus des fonctionnalités CRUD, j'ai également ajouté une couche de sécurité en implémentant un système de login pour sécuriser les API.

        5. **Implémentation de JWT :** 

        J'ai également mis en place JSON Web Token (JWT) pour générer et valider les jetons d'authentification, renforçant ainsi la sécurité du système de connexion.

        **Remarque :** Le mini-projet est sécurisé. Pour tester les fonctionnalités CRUD avec Postman ou Swagger, l'utilisateur doit d'abord se connecter pour générer le jeton d'authentification. Les identifiants pour la connexion sont les suivants :

        Nom d'utilisateur : Admin
        Mot de passe : 1234

















# [Java][Spring] Dream Case App - Restful API

## Objectif

L’objectif de ce mini projet est de créer une application Restful Web Service à l'aide de Spring Boot, et ayant 4 fonctionnements Create, Read, Update, Delete (CRUD): 

### Read (GET method)
Construire un URI pour traiter les demandes (request) dans le but de retourner un case (dossier) avec son unique ID. Ce URI n'accepte que les demandes avec la méthode GET. Les données attachées de cette demande est l'information du case crée. Elle est sous format de JSON.

=> GET http://localhost:8080/cases/E01

### Update (PUT method)
Construire un URI pour traiter les demandes (request) dans le but de modifier les informations d'un case (dossier). Ce URI n'accepte que les demandes avec la méthode PUT.

=> PUT http://localhost:8080/cases/E01

### Create (POST method)
Construire un URI pour traiter les demandes (request) dans le but de créer un case (dossier). Ce URI n'accepte que les demandes avec la méthode POST. Les données attachées de cette demande est l'information du case crée. Elle est sous format de JSON.

=> POST http://localhost:8080/cases/

### Delete (DELETE method).
Construisez un URI pour traiter une demande (request) de suppression d'un case (dossier). Ce URI n'accepte que les demandes utilisant la méthode DELETE.

=> DELETE http://localhost:8080/cases/E01

## Structure de données  

Chaque case doit avoir les informations suivantes : 

|   Nom du champ  |     Type        |     Description               |                    
|:---------------:|:---------------:|:-----------------------------:|
| caseId          | BIGINT (PK)     |  Id du case                   |
| creationDate    | DATETIME        |  Date de création du case     |
| lastUpdateDate  | DATETIME        |  Date de modification du case |
| title           | VARCHAR(255)    |  Titre du case                |
| description     | VARCHAR(2056)   |  Description  du case         |
 

## Prérequis

|Langage   |Framework    | Type de Base de données          | Pattern|                     
|:--------:|:-----------:|:--------------------------------:|:------:|
| Java 11  | Spring Boot |  SQL (MySQL ou PotgreSQL) H2 DB* | MVC    | 

---> H2 DB*: il faut utiliser le framework H2 embatrqué afin de tester le projet

## Livraison attendue 

- Zip conteant le projet Spring Boot compilable avec Maven ou Gradle avec le code source en JAVA ainsi minimum 2 testes unitaires.

## Durée de réalisation 

- Le développement de ce mini projet est estimé à 1H30 MAX

## Références

- https://spring.io/projects/spring-boot
- https://www.javatpoint.com/restful-web-services-spring-boot
