# Programme de la Formation

## Le conteneur Spring
- Pratiques de conception.
- Spring dans l'écosystème Jakarta EE.
- Les composants essentiels : Core, accès aux données, WebMVC.
- L'intégration aux autres technologies.
- L’apport de Spring Boot : 
  - La gestion des dépendances Maven.
  - La configuration automatique.
- L’environnement de développement.
- Les stratégies de déploiement : 
  - JAR, 
  - image OCI, 
  - application native.

---

## Gestion des beans avec Spring Core et Spring Boot
- Le découpage en couches, l'approche POJO.
- La gestion de l'état.
- L'injection de dépendances.
- Les intercepteurs et la programmation orientée aspect.
- Le cache et la supervision avec JMX.
- Les invocations planifiées.
- Les profils.
- Tests des beans Spring.

**Travaux pratiques :**  
Créer une application n-tiers avec Spring Core et Spring Boot.

---

## Accès aux données et gestion des transactions
- L’accès à une base de données relationnelle depuis une application Spring.
- La gestion des transactions.
- Le support des transactions dans les tests.
- Introduction à Spring Data.

**Travaux pratiques :**  
Mise en place d’une couche d'accès aux données avec Spring.

---

## API REST avec Spring MVC et Spring WebFlux
- Bonnes pratiques de conception d'une API REST.
- Mise en place de l'API REST.
- La validation avec l'API Jakarta Validation.
- La gestion des exceptions.
- Introduction à la programmation réactive avec Spring WebFlux.

**Travaux pratiques :**  
Développer une API exposant les services développés précédemment.  
Consommation des Web Services REST par un client HTML/JavaScript.

---

## IHM Web avec Spring MVC
- Rappel du pattern MVC.
- La validation avec l’API Bean Jakarta Validation.
- Les vues :
  - Accès au modèle,
  - Internationalisation,
  - Gestion des exceptions.

**Travaux pratiques :**  
Création d'une application Web exposant la couche métier développée précédemment.

---

## Spring Security
- Définition d'un référentiel utilisateurs.
- Modes d'authentification (Session, JWT).
- Sécurisation des routes.
- Tests d'une application sécurisée.

**Travaux pratiques :**  
Application de la sécurité sur le projet Web.

---

## Échanges de messages avec Spring WebSocket
- Théorie, design patterns, le principe Pub/Sub.
- Présentation de STOMP et SockJS.
- Mise en place côté serveur et côté client.

**Travaux pratiques :**  
Création d’un mécanisme de Publish/Subscribe entre une application Spring MVC et un front-end HTML/JavaScript.
