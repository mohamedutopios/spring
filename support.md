---

marp: true
title: Kubernetes
theme: utopios
paginate: true
author: Mohamed Aijjou
header: "![h:70px](https://utopios-marp-assets.s3.eu-west-3.amazonaws.com/logo_blanc.svg)"
footer: "Utopios® Tous droits réservés"

---

<!-- _class: lead -->
<!-- _paginate: false -->

# Spring, développer des applications d'entreprise

---

## Sommaire


1. Histoire de Spring : Origine et Évolution
2. Le conteneur Spring
3. Gestion des beans avec Spring Core et Spring boot.
4. Accès aux données et gestion des transactions
5. API REST avec Spring MVC et Spring WebFlux
6. IHM Web avec Spring MVC
7. Spring Security
8. Sécurité et détection d'attaques

</div>

---

<!-- _class: lead -->
<!-- _paginate: false -->

## Histoire de Spring : Origine et Évolution

---

## Histoire de Spring : Origine et Évolution

#### **Introduction :**

<br/>

<div style="font-size:30px">

- Spring est l’un des frameworks Java les plus influents et les plus utilisés. 
- Son développement a été motivé par des limitations perçues dans les technologies Java d'entreprise des années 2000.

</div>

---

## Histoire de Spring : Origine et Évolution

#### **Origines de Spring**



<div style="font-size:21px">
<br>

**Contexte des années 1990-2000** :
  - Les entreprises utilisaient largement **Java EE** (Java 2 Platform, Enterprise Edition, ou J2EE) pour développer des applications d'entreprise.
  - J2EE avait une approche rigide, centrée sur les EJB (Enterprise JavaBeans), avec une gestion complexe des transactions, des dépendances, et un développement fortement couplé au conteneur d'exécution.
  <br>

**Les limitations de J2EE** :
  - **Complexité** : Le développement avec J2EE nécessitait beaucoup de code "verbeux" pour accomplir des tâches simples.
  - **Rigidité** : Les applications étaient fortement liées au conteneur d'application, ce qui rendait le test en isolation difficile.
  - **Manque de flexibilité** : Les EJB imposaient une structure contraignante, rendant difficile l'adoption d'autres solutions comme Hibernate pour la persistance.


</div>



---

## Histoire de Spring : Origine et Évolution

#### **Origines de Spring**

<div style="font-size:26px">
<br>

**Naissance de Spring** :
  - **Rod Johnson**, un développeur Java et auteur australien, a critiqué ces limitations dans son livre *"Expert One-on-One J2EE Design and Development"* publié en 2002.
  - Dans ce livre, il a présenté un framework léger basé sur des concepts comme l'**Inversion de Contrôle (IoC)** et l'**Injection de Dépendances (DI)**, qui deviendrait la base de Spring.
  - La première version de Spring Framework a été publiée en 2003 sous licence Apache 2.0.


</div>


---

## Histoire de Spring : Origine et Évolution

#### **Croissance et Adoption (2003-2010)**

<div style="font-size:21px">
<br>

**Version 1.0 (2004)** :
  - La version 1.0 de Spring Framework a introduit des fonctionnalités clés comme :
    - **IoC Container** : Gestion des dépendances via XML.
    - **AOP (Aspect-Oriented Programming)** : Gestion des préoccupations transversales comme les transactions et le logging.
    - **Abstraction JDBC** : Simplification de l'accès aux bases de données.
  - Spring est rapidement adopté par les développeurs frustrés par la complexité de J2EE.

<br>

**Support pour Hibernate** :
  - L'intégration avec Hibernate a permis de simplifier l'accès aux bases de données.
  - Cela a renforcé l'attractivité de Spring auprès des développeurs cherchant une alternative à l'approche rigide des EJB.


</div>


---

## Histoire de Spring : Origine et Évolution

#### **Croissance et Adoption (2003-2010)**

<div style="font-size:24px">
<br>

**Version 2.0 (2006)** :
  - Introduction de nouvelles fonctionnalités :
    - Gestion améliorée des **annotations** pour réduire les configurations XML.
    - Introduction du concept de **Spring Modules**, permettant une modularité accrue.
    - Améliorations dans l'intégration avec d'autres frameworks, comme Struts et JSF pour les applications web.
<br>

**Compétition avec Java EE** :
  - Java EE a commencé à simplifier ses API avec des annotations dans les EJB 3.0, mais Spring a maintenu son avantage grâce à sa flexibilité et à son approche légère.


</div>


---

## Histoire de Spring : Origine et Évolution

#### **L'Ère de Spring Boot (2010-présent)**

<div style="font-size:27px">
<br>

**Contexte des années 2010** :
  - L'industrie évolue vers les **microservices** et les **applications cloud-native**.
  - Les développeurs cherchent à éviter les tâches répétitives de configuration et à accélérer le développement.

<br>

**Version 3.0 (2009)** :
  - Support des **annotations Java 5** pour réduire davantage la configuration XML.
  - Intégration des standards Java EE comme JPA 2.0, Bean Validation, et RESTful services.


</div>
---

## Histoire de Spring : Origine et Évolution

#### **Spring Aujourd'hui : Écosystème et Réalité Moderne**

<div style="font-size:21px">
<br>

**Introduction de Spring Boot (2014)** :
  - Spring Boot, une extension de Spring Framework, est lancé pour simplifier et accélérer le développement d'applications Java.
  - **Fonctionnalités clés** :
    - **Dépendances pré-configurées** : Starters Maven/Gradle.
    - **Configuration automatique** pour les bases de données, serveurs web, etc.
    - Serveurs web intégrés comme Tomcat ou Jetty.
    - Possibilité de créer des applications packagées en **fichiers JAR exécutables**.

<br>

**Adoption massive** :
  - Spring Boot a révolutionné le développement Java d'entreprise, devenant un choix incontournable pour les microservices et les applications cloud-native.


</div>


---

## Histoire de Spring : Origine et Évolution

#### **L'Ère de Spring Boot (2010-présent)**

<div style="font-size:21px">
<br>

**Spring Framework 5 (2017)** :
  - Introduction de **Spring WebFlux**, un module réactif basé sur Project Reactor, pour supporter les architectures asynchrones et non-bloquantes.
  - Support de **Java 8+** pour tirer parti des lambdas et des Streams.

<br>

**Écosystème Spring** :
  - Spring est devenu bien plus qu'un simple framework :
    - **Spring Data** : Simplifie l'accès aux bases de données SQL et NoSQL.
    - **Spring Security** : Fournit des outils puissants pour sécuriser les applications.
    - **Spring Cloud** : Une suite de solutions pour développer et déployer des applications cloud-native.
    - **Spring Batch** : Support pour les tâches de traitement par lots.
    - **Spring Integration** : Facilite l'intégration des systèmes.

</div>

---

## Histoire de Spring : Origine et Évolution

#### **L'Ère de Spring Boot (2010-présent)**

<div style="font-size:28px">

<br>

**Support pour les nouvelles tendances** :
  - Spring continue d’évoluer pour répondre aux besoins modernes :
    - Développement de **microservices**.
    - Intégration avec **Docker**, **Kubernetes**, et les plateformes cloud comme AWS, Azure, et Google Cloud.
    - Support des paradigmes réactifs pour des applications haute performance.

</div>

---

## Histoire de Spring : Origine et Évolution

#### **Impact de Spring**

<div style="font-size:35px">
<br>



Spring a transformé le paysage du développement Java en :
- Simplifiant le développement d'applications complexes.
- Rendant Java compétitif face à d'autres langages comme Python, Node.js, ou Go.
- Permettant une adoption massive des microservices et des architectures modernes.

</div>

---


<!-- _class: lead -->
<!-- _paginate: false -->

## Le conteneur Spring

---





## Le conteneur Spring

#### **Fonctionnement général du conteneur Spring**

<br>
<div style="font-size:28px">

**Le conteneur IoC (Inversion of Control)** :
  - Le conteneur Spring est responsable de :
    - **Gérer le cycle de vie des objets (Beans)**.
    - **Configurer et assembler automatiquement les dépendances**.
    - Fournir un **contexte d'exécution cohérent** pour les composants de l'application.
  - Principe clé : L'application ne crée pas elle-même ses dépendances ; elles sont injectées par le conteneur.

</div>

---

## Le conteneur Spring

#### **Fonctionnement général du conteneur Spring**

<br>
<div style="font-size:21px">

**Processus de fonctionnement** :
  1. **Déclaration des Beans** :
     - Les composants sont définis dans un fichier de configuration XML, des annotations ou via une classe de configuration Java.
  2. **Chargement du conteneur** :
     - Le conteneur lit les définitions des Beans et crée une instance pour chaque Bean, selon la portée définie.
  3. **Injection de dépendances** :
     - Les dépendances sont résolues et injectées dans les Beans (via constructeur, setter ou annotations).
  4. **Gestion du cycle de vie** :
     - Les Beans peuvent être initialisés, détruits ou recréés en fonction de leur portée.
</div>

---

## Le conteneur Spring

#### **Fonctionnement général du conteneur Spring**

<br>
<div style="font-size:29px">

**Types de conteneurs** :
  - **BeanFactory** : Conteneur minimaliste offrant les fonctionnalités de base de l'IoC.
  - **ApplicationContext** : Extension du BeanFactory, avec des fonctionnalités avancées comme l'internationalisation, la gestion des événements et l'intégration avec les frameworks web.
</div>

---

## Le conteneur Spring

#### **Fonctionnement général du conteneur Spring**


<div style="font-size:29px">

<center>
<img src="./assets/spring-container.png" width="550px">
</center>

</div>

---

## Le conteneur Spring

#### **Spring dans l'écosystème Jakarta EE**


<div style="font-size:25px">

<br>

**Jakarta EE : Définition et évolution**
  - Anciennement Java EE, Jakarta EE est un ensemble de spécifications pour le développement d'applications d'entreprise.
  - Principales spécifications : JPA, JAX-RS, CDI, JMS, Servlet, etc.
  - Fonctionnement basé sur des serveurs d’applications comme WildFly, Payara, et TomEE.
<br>

**Spring : Complément ou alternative ?**
  - Spring est souvent utilisé comme alternative légère et modulaire à Jakarta EE.
  - Spring Boot permet de créer des applications sans dépendre d’un serveur d'applications Jakarta EE, tout en supportant ses spécifications clés.
</div>

---

## Le conteneur Spring

#### **Spring dans l'écosystème Jakarta EE**

<div style="font-size:22px">

#### **a. JPA (Jakarta Persistence API)**
- **Utilisation avec Spring Data JPA :**
  - Jakarta EE définit JPA pour la persistance des données relationnelles.
  - Spring intègre JPA via Spring Data JPA, qui simplifie l’interaction avec les bases de données.
  - Exemple d’intégration :
    ```java
    @Entity
    public class Product {
        @Id
        @GeneratedValue
        private Long id;
        private String name;
    }

    @Repository
    public interface ProductRepository extends JpaRepository<Product, Long> { }
    ```
</div>

---

## Le conteneur Spring

#### **Spring dans l'écosystème Jakarta EE**

<div style="font-size:25px">

#### **a. JPA (Jakarta Persistence API)**
- **Gestion des transactions avec Spring :**
  - Utilisation de l'annotation `@Transactional`.
  - Spring offre une abstraction plus flexible pour gérer les transactions.
    ```java
    @Service
    public class ProductService {
        @Transactional
        public void saveProduct(Product product) {
            productRepository.save(product);
        }
    }
    ```
</div>

---

## Le conteneur Spring

#### **Spring dans l'écosystème Jakarta EE**

<div style="font-size:23px">

#### **b. JAX-RS (Jakarta RESTful Web Services)**
- **Spring Web MVC vs JAX-RS :**
  - Jakarta EE propose JAX-RS pour développer des API REST.
  - Spring propose Spring Web MVC qui couvre les mêmes fonctionnalités, mais avec une approche plus intégrée dans l’écosystème Spring.
  - Exemple avec Spring Web MVC :
    ```java
    @RestController
    @RequestMapping("/products")
    public class ProductController {
        @GetMapping
        public List<Product> getAllProducts() {
            return productService.getAllProducts();
        }
    }
    ```

- **Interopérabilité :**
  - Il est possible d’utiliser des implémentations JAX-RS comme Jersey ou RestEasy avec Spring Boot.
</div>

---
## Le conteneur Spring

#### **Spring dans l'écosystème Jakarta EE**

<div style="font-size:23px">

#### **c. JMS (Jakarta Messaging Services)**
- **Spring JMS :**
  - Jakarta EE utilise JMS pour la communication asynchrone avec des files d’attente (queues) ou des sujets (topics).
  - Spring JMS fournit une abstraction simplifiée pour interagir avec JMS.
  - Exemple avec Spring JMS :
    ```java
    @Component
    public class MessageListener {
        @JmsListener(destination = "order-queue")
        public void processOrder(String message) {
            System.out.println("Received message: " + message);
        }
    }
    ```
</div>

---

## Le conteneur Spring

#### **Spring dans l'écosystème Jakarta EE**

<div style="font-size:29px">

<br>

#### **d. Servlet (Jakarta Servlet API)**
- Spring repose sur la spécification Jakarta Servlet pour gérer les requêtes HTTP.
- Exemple d’utilisation :
  - Spring Boot utilise le conteneur servlet intégré comme Tomcat ou Jetty.

</div>

---

## Le conteneur Spring

#### **Spring dans l'écosystème Jakarta EE**

<div style="font-size:25px">


### Déploiement dans des environnements Jakarta EE**
- **Spring dans un serveur d'applications Jakarta EE :**
  - Les applications Spring peuvent être déployées dans des serveurs Jakarta EE comme WildFly, Payara, ou TomEE.
  - Les fichiers `application.properties` ou `application.yml` permettent une configuration flexible adaptée au déploiement.

- **Spring Boot comme alternative autonome :**
  - Spring Boot propose un conteneur intégré (Tomcat, Jetty) qui rend inutile l’utilisation d’un serveur Jakarta EE.
  - Cela simplifie le développement et le déploiement des applications.
</div>

---

## Le conteneur Spring

#### **Spring dans l'écosystème Jakarta EE**

| **Aspect**              | **Spring**                                              | **Jakarta EE**                                          |
|-------------------------|-------------------------------------------------------|-------------------------------------------------------|
| **Configuration**       | Flexible (annotations, Java, YAML, XML)                | Basé sur XML et annotations standardisées             |
| **Conteneur**           | Léger, intégré (Tomcat, Jetty)                          | Dépend d’un serveur d’applications                   |
| **Communauté**          | Actif, nombreuses extensions et plugins                | Basé sur des standards, moins rapide à évoluer        |
| **Abstraction**         | Plus riche (Spring Data, Spring Security)              | Moins d’abstractions, plus près des spécifications    |
| **Approche**            | Modularité, convention plutôt que configuration       | Standards, nécessite souvent des configurations       |



---

### Le conteneur Spring


#### Les composants essentiels : Core, accès aux données, WebMVC.

<div style="font-size:20px">

### **1. Spring Core**
Le module **Core** constitue le cœur du framework Spring. Il fournit les fonctionnalités fondamentales utilisées par tous les autres modules.

#### **a. Conteneur IoC (Inversion of Control)**
- **Concept :**
  - Gestion des objets (appelés Beans) et de leurs dépendances par le conteneur Spring.
  - Principe de l’injection de dépendances (DI).
- **Fonctionnalités clés :**
  - Instanciation des Beans.
  - Gestion du cycle de vie des Beans.
  - Résolution des dépendances entre Beans.

</div>

---

### Le conteneur Spring


#### Les composants essentiels : Core, accès aux données, WebMVC.

<div style="font-size:25px">

### **1. Spring Core**
Le module **Core** constitue le cœur du framework Spring. Il fournit les fonctionnalités fondamentales utilisées par tous les autres modules.

#### **a. Conteneur IoC (Inversion of Control)**
- **Annotations courantes :**
  - `@Component`, `@Service`, `@Repository`, `@Controller` : Déclarer les Beans dans le conteneur.
  - `@Autowired` : Injecter les dépendances automatiquement.
  - `@Qualifier` : Résoudre les conflits de Beans.

</div>

---

### Le conteneur Spring


#### Les composants essentiels : Core, accès aux données, WebMVC.

<div style="font-size:17px">

### **1. Spring Core**
Le module **Core** constitue le cœur du framework Spring. Il fournit les fonctionnalités fondamentales utilisées par tous les autres modules.

#### **b. Gestion des configurations**
- Approches disponibles :
  - **XML Configuration** (ancienne méthode, encore supportée).
  - **Java Configuration** : Utilisation de classes annotées avec `@Configuration`.
    ```java
    @Configuration
    public class AppConfig {
        @Bean
        public MyService myService() {
            return new MyServiceImpl();
        }
    }
    ```
  - **Annotations** : Détection automatique des composants via `@ComponentScan`.
</div>

---

### Le conteneur Spring


#### Les composants essentiels : Core, accès aux données, WebMVC.

<div style="font-size:16px">

### **1. Spring Core**
Le module **Core** constitue le cœur du framework Spring. Il fournit les fonctionnalités fondamentales utilisées par tous les autres modules.

#### **c. Gestion des événements**
- Permet la communication entre différents composants au sein du conteneur Spring.
- Exemple avec un événement personnalisé :
  ```java
  public class CustomEvent extends ApplicationEvent {
      public CustomEvent(Object source) {
          super(source);
      }
  }

  @Component
  public class CustomEventListener {
      @EventListener
      public void handleCustomEvent(CustomEvent event) {
          System.out.println("Custom event received: " + event.getSource());
      }
  }
  ```
</div>

---

### Le conteneur Spring


#### Les composants essentiels : Core, accès aux données, WebMVC.

<div style="font-size:16px">

### **2. Spring pour l’Accès aux Données**
Spring facilite l’interaction avec les bases de données grâce à des abstractions puissantes.

#### **a. JDBC Template**
- Simplifie l’utilisation de JDBC (Java Database Connectivity).
- Élimine le besoin de gérer manuellement les connexions et exceptions.
- Exemple :
  ```java
  @Repository
  public class UserRepository {
      private final JdbcTemplate jdbcTemplate;

      public UserRepository(JdbcTemplate jdbcTemplate) {
          this.jdbcTemplate = jdbcTemplate;
      }

      public List<User> findAll() {
          return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
      }
  }
  ```
</div>

---

### Le conteneur Spring


#### Les composants essentiels : Core, accès aux données, WebMVC.

<div style="font-size:18px">

### **2. Spring pour l’Accès aux Données**
Spring facilite l’interaction avec les bases de données grâce à des abstractions puissantes.

#### **b. ORM avec Spring Data JPA**
- Fournit une intégration avec JPA (Jakarta Persistence API).
- Simplifie la création de repositories grâce aux interfaces.
- Exemple avec une entité et un repository :
  ```java
  @Entity
  public class Product {
      @Id
      @GeneratedValue
      private Long id;
      private String name;
  }

  public interface ProductRepository extends JpaRepository<Product, Long> { }
  ```
</div>

---

### Le conteneur Spring


#### Les composants essentiels : Core, accès aux données, WebMVC.

<div style="font-size:19px">

### **2. Spring pour l’Accès aux Données**
Spring facilite l’interaction avec les bases de données grâce à des abstractions puissantes.

#### **c. Gestion des Transactions**
- Spring gère les transactions via l’annotation `@Transactional`.
- Exemple :
  ```java
  @Service
  public class OrderService {
      @Transactional
      public void placeOrder(Order order) {
          orderRepository.save(order);
          paymentService.processPayment(order);
      }
  }
  ```
</div>

---

### Le conteneur Spring


#### Les composants essentiels : Core, accès aux données, WebMVC.

<div style="font-size:22px">

### **2. Spring pour l’Accès aux Données**
Spring facilite l’interaction avec les bases de données grâce à des abstractions puissantes.

#### **d. Cache intégré**
- Support pour le caching avec des annotations comme `@Cacheable` et `@CacheEvict`.
- Exemple :
  ```java
  @Service
  public class ProductService {
      @Cacheable("products")
      public Product findProductById(Long id) {
          return productRepository.findById(id).orElseThrow();
      }
  }
  ```
</div>

---

### Le conteneur Spring


#### Les composants essentiels : Core, accès aux données, WebMVC.

<div style="font-size:14px">

### **3. Spring WebMVC**
Le module **WebMVC** est conçu pour développer des applications web, principalement RESTful, avec une architecture basée sur le modèle MVC (Model-View-Controller).

#### **a. Composants principaux de Spring WebMVC**
1. **Controller :**
   - Reçoit les requêtes HTTP et appelle les services appropriés.
   - Exemple avec un contrôleur REST :
     ```java
     @RestController
     @RequestMapping("/products")
     public class ProductController {
         private final ProductService productService;

         public ProductController(ProductService productService) {
             this.productService = productService;
         }

         @GetMapping
         public List<Product> getAllProducts() {
             return productService.findAll();
         }
     }
     ```

</div>

---

### Le conteneur Spring


#### Les composants essentiels : Core, accès aux données, WebMVC.

<div style="font-size:17px">

### **3. Spring WebMVC**

#### **a. Composants principaux de Spring WebMVC**
2. **Service :**
   - Contient la logique métier, appel des repositories et gestion des règles spécifiques.
   - Exemple :
     ```java
     @Service
     public class ProductService {
         private final ProductRepository productRepository;

         public ProductService(ProductRepository productRepository) {
             this.productRepository = productRepository;
         }

         public List<Product> findAll() {
             return productRepository.findAll();
         }
     }
     ```
</div>

---

### Le conteneur Spring


#### Les composants essentiels : Core, accès aux données, WebMVC.

<div style="font-size:30px">

### **3. Spring WebMVC**

#### **a. Composants principaux de Spring WebMVC**
3. **View :**
   - Renvoie les réponses HTTP (HTML, JSON, XML, etc.).
   - Dans les applications REST, les vues sont remplacées par des réponses JSON ou XML via des librairies comme Jackson.
</div>

---
### Le conteneur Spring


#### Les composants essentiels : Core, accès aux données, WebMVC.

<div style="font-size:23px">

### **3. Spring WebMVC**

#### **b. Gestion des routes**
- Configuration des routes avec les annotations `@RequestMapping`, `@GetMapping`, `@PostMapping`, etc.
- Exemple :
  ```java
  @RestController
  @RequestMapping("/users")
  public class UserController {
      @GetMapping("/{id}")
      public User getUser(@PathVariable Long id) {
          return userService.findById(id);
      }
  }
  ```
</div>

---

### Le conteneur Spring


#### Les composants essentiels : Core, accès aux données, WebMVC.

<div style="font-size:25px">

### **3. Spring WebMVC**

#### **c. Validation des entrées**
- Utilisation des annotations de validation comme `@Valid` et des contraintes comme `@NotNull`, `@Size`.
- Exemple :
  ```java
  @PostMapping
  public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
      return ResponseEntity.ok(userService.save(user));
  }
  ```
</div>

---

### Le conteneur Spring


#### Les composants essentiels : Core, accès aux données, WebMVC.

<div style="font-size:25px">

### **3. Spring WebMVC**

#### **d. Gestion des exceptions**
- Centralisation de la gestion des exceptions avec `@ControllerAdvice`.
- Exemple :
  ```java
  @ControllerAdvice
  public class GlobalExceptionHandler {
      @ExceptionHandler(ResourceNotFoundException.class)
      public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
      }
  }
  ```
</div>

---

### Le conteneur Spring


#### Les composants essentiels : Core, accès aux données, WebMVC.

<div style="font-size:26px">

### **3. Spring WebMVC**

#### **e. Support des formats multiples**
- Spring WebMVC supporte automatiquement JSON, XML, et HTML en fonction du `Content-Type` de la requête.
- Exemple avec JSON (via Jackson) :
  ```java
  @GetMapping("/product/{id}")
  public Product getProduct(@PathVariable Long id) {
      return productService.findProductById(id);
  }
  ```
</div>

---

### Le conteneur Spring


#### L'intégration aux autres technologies

<div style="font-size:27px">

<br>

**Spring : Un framework intégrateur**
  - Spring est conçu pour être extensible et interopérable avec d'autres technologies.
  - Permet de simplifier l'utilisation de bibliothèques complexes grâce à des abstractions.

<br>

**Avantages de l’intégration avec Spring :**
  - Configuration simplifiée.
  - Support natif pour plusieurs technologies via des modules spécialisés.
  - Flexibilité pour ajouter ou remplacer des technologie

</div>

---

### Le conteneur Spring


#### L'intégration aux autres technologies

<div style="font-size:30px">

<br>

### **1. Bases de données**
- **JDBC** : Simplifié via `JdbcTemplate` pour éviter la gestion manuelle des connexions.
- **Spring Data JPA** : Intégration avec JPA pour la persistance relationnelle via des repositories simples.
- **NoSQL** : Support pour MongoDB, Redis, Cassandra avec Spring Data.
  ```java
  public interface CustomerRepository extends MongoRepository<Customer, String> {}
  ```
</div>

---


### Le conteneur Spring


#### L'intégration aux autres technologies

<div style="font-size:25px">

### **2. Frameworks web**
- **Spring WebMVC** : Architecture RESTful et MVC avec annotations comme `@Controller` et `@RestController`.
- **Thymeleaf** : Génération dynamique des vues HTML.
- **WebFlux** : Support des applications réactives non bloquantes.
  ```java
  @RestController
  public class ReactiveController {
      @GetMapping("/flux")
      public Flux<String> getFlux() {
          return Flux.just("Spring", "WebFlux", "Reactivity");
      }
  }
  ```
</div>

---

### Le conteneur Spring


#### L'intégration aux autres technologies

<div style="font-size:30px">

### **3. Messagerie**
- **JMS** : Gestion des files d'attente avec `@JmsListener`.
- **Kafka** : Support via Spring Kafka pour produire et consommer des messages.
  ```java
  @KafkaListener(topics = "topicName", groupId = "groupId")
  public void listen(String message) {
      System.out.println("Received: " + message);
  }
  ```

</div>

---

### Le conteneur Spring


#### L'intégration aux autres technologies

<div style="font-size:30px">

### **4. Sécurité**
- **Spring Security** : Authentification, autorisation, gestion des sessions.
- **OAuth2/OpenID Connect** : Gestion simplifiée des autorisations via Spring Security OAuth2.
  ```java
  http.oauth2Login();
  ```

</div>

---

### Le conteneur Spring


#### L'intégration aux autres technologies

<div style="font-size:30px">

### **5. Cloud et DevOps**
- **Spring Cloud** : Services comme Eureka (discovery), Config Server (configurations centralisées), Gateway API.
- **AWS/GCP** : Intégration avec des services cloud (S3, DynamoDB, Pub/Sub).
  ```java
  amazonS3.putObject(bucketName, fileName, input, new ObjectMetadata());
  ```

</div>

---

### Le conteneur Spring


#### L'intégration aux autres technologies

<div style="font-size:22px">

### **6. Monitoring et logging**
- **Spring Boot Actuator** : Points d’accès pour la surveillance (santé, métriques).
- **Prometheus/Grafana** : Exportation des métriques pour le monitoring.
  ```plaintext
  /actuator/health
  ```
- Exportation des métriques pour Prometheus.
- Exemple :
  ```java
  @RestController
  @RequestMapping("/metrics")
  public class MetricsController {
      @GetMapping("/custom")
      public String customMetric() {
          return "Custom metric for monitoring";
      }
  }
  ```

</div>

---

### Le conteneur Spring


#### L'intégration aux autres technologies

<div style="font-size:30px">

### **7. Tests**
- **JUnit et Mockito** : Tests unitaires avec mocks.
- **Spring Boot Test** : Tests d’intégration avec le contexte Spring.
  ```java
  @SpringBootTest
  public class ApplicationTests {
      @Test
      void contextLoads() { }
  }
  ```
</div>

---

### Le conteneur Spring


#### L'intégration aux autres technologies

<div style="font-size:30px">

### **8. ESB et SOA**
- **Apache Camel** : Intégration et orchestration des services.
- Exemple :
  ```java
  @Component
  public class MyRoute extends RouteBuilder {
      @Override
      public void configure() {
          from("direct:start")
              .to("log:myLogger");
      }
  }
  ```

</div>

---

## Apache HTTPD 2.4 : rappels et nouveautés

#### **Nouveaux types de contextes dans Apache HTTPD 2.4**


<div style="font-size:18px">

Apache HTTPD 2.4 introduit des fonctionnalités permettant une gestion plus fine des configurations grâce à de nouveaux **types de contextes**. Ces contextes permettent de personnaliser les comportements selon des conditions ou des environnements spécifiques.

#### **Principaux contextes et leur usage :**

1. **Directives conditionnelles avec `<If>` :**
   - Permet d'évaluer des conditions dynamiques dans les fichiers de configuration.
   - Syntaxe :
     ```conf
     <If "%{HTTP_HOST} == 'example.com'">
       DocumentRoot "/var/www/example"
     </If>
     ```
   - Supporte les expressions complexes :
     - Accès à des variables d'environnement (`%{ENV:VARIABLE}`).
     - Comparaisons entre en-têtes HTTP (`%{HTTP_*}`).
     - Expression booléenne complexe (`&&`, `||`, `!`).
</div>

---

## Apache HTTPD 2.4 : rappels et nouveautés

#### **Nouveaux types de contextes dans Apache HTTPD 2.4**


<div style="font-size:27px">

#### **Principaux contextes et leur usage :**

2. **Directives dynamiques avec `<ElseIf>` et `<Else>` :**
   - Exemple :
     ```conf
     <If "%{HTTP_HOST} == 'example.com'">
       DocumentRoot "/var/www/example"
     <ElseIf "%{HTTP_HOST} == 'example.org'">
       DocumentRoot "/var/www/example_org"
     <Else>
       DocumentRoot "/var/www/default"
     </Else>
     ```
</div>

---

## Apache HTTPD 2.4 : rappels et nouveautés

#### **Nouveaux types de contextes dans Apache HTTPD 2.4**


<div style="font-size:28px">

#### **Principaux contextes et leur usage :**

3. **Directives `IncludeOptional` :**
   - Permet d’inclure des fichiers optionnels, évitant les erreurs si un fichier est absent.
   - Exemple :
     ```conf
     IncludeOptional conf.d/*.conf
     ```

</div>

---

## Apache HTTPD 2.4 : rappels et nouveautés

#### **Nouveaux types de contextes dans Apache HTTPD 2.4**


<div style="font-size:28px">

#### **Principaux contextes et leur usage :**

4. **Directives spécifiques au trafic avec `<IfModule>` :**
   - Active une configuration seulement si un module est chargé.
   - Exemple :
     ```conf
     <IfModule mod_ssl.c>
       Listen 443
       SSLEngine on
     </IfModule>
     ```

</div>

---

## Apache HTTPD 2.4 : rappels et nouveautés

#### **Panorama des modules d'Apache 2.4**


<div style="font-size:30px">

Apache HTTPD 2.4 offre une large gamme de modules pour diverses fonctionnalités. Voici un aperçu des catégories principales et des modules les plus utiles.

#### **Catégorie : Performance**
- **`mod_http2`** : Support HTTP/2.
- **`mod_cache`** : Mise en cache des contenus.
- **`mod_deflate`** : Compression des contenus.
- **`mod_expires`** : Contrôle des en-têtes de cache HTTP.
</div>

---

## Apache HTTPD 2.4 : rappels et nouveautés

#### **Panorama des modules d'Apache 2.4**


<div style="font-size:30px">

#### **Catégorie : Sécurité**
- **`mod_ssl`** : Support SSL/TLS.
- **`mod_security`** : Protection contre les attaques (pare-feu applicatif).
- **`mod_authz_core`** : Cadre centralisé pour l'autorisation.
- **`mod_authn_dbd`** : Authentification via base de données.
</div>

---

## Apache HTTPD 2.4 : rappels et nouveautés

#### **Panorama des modules d'Apache 2.4**


<div style="font-size:28px">

#### **Catégorie : Proxy**
- **`mod_proxy`** : Support du proxy HTTP/HTTPS.
- **`mod_proxy_http`** : Proxy pour le trafic HTTP.
- **`mod_proxy_balancer`** : Équilibrage de charge.
- **`mod_proxy_wstunnel`** : Proxy pour WebSocket.

#### **Catégorie : Journalisation**
- **`mod_log_config`** : Configuration avancée des logs.
- **`mod_logio`** : Ajoute des informations sur la taille des réponses dans les logs.
</div>

---

## Apache HTTPD 2.4 : rappels et nouveautés

#### **Panorama des modules d'Apache 2.4**


<div style="font-size:28px">

#### **Catégorie : Réécriture et redirection**
- **`mod_rewrite`** : Réécriture d'URL avancée.
- **`mod_alias`** : Redirection simple.

#### **Catégorie : Autres fonctionnalités**
- **`mod_cgi`** : Support des scripts CGI.
- **`mod_dav`** : Prise en charge du protocole WebDAV.
- **`mod_headers`** : Modification des en-têtes HTTP.
- **`mod_dir`** : Gestion des répertoires (pages index).

</div>

---

## Apache HTTPD 2.4 : rappels et nouveautés

#### **Panorama des modules d'Apache 2.4**


<div style="font-size:35px">

#### **Gestion des modules dynamiques :**
- Les modules sont chargés dynamiquement via la directive `LoadModule` :
  ```conf
  LoadModule rewrite_module modules/mod_rewrite.so
  LoadModule ssl_module modules/mod_ssl.so
  ```
- Vérifiez les modules chargés :
  ```bash
  apachectl -M
  ```

</div>

---

## Apache HTTPD 2.4 : rappels et nouveautés

#### **Support du protocole HTTP/2**


<div style="font-size:28px">

Apache HTTPD 2.4 introduit le support natif du protocole **HTTP/2** via le module `mod_http2`. HTTP/2 est conçu pour améliorer les performances des sites web modernes.

#### **Avantages de HTTP/2 :**
- **Multiplexage des requêtes** : Plusieurs requêtes/connexions parallèles via une seule connexion TCP.
- **Compression des en-têtes** : Réduction de la taille des en-têtes HTTP grâce à HPACK.
- **Priorisation des requêtes** : Optimisation de l'ordre d'exécution.
- **Push serveur** : Envoi proactif de ressources avant la requête client.

</div>

---

## Apache HTTPD 2.4 : rappels et nouveautés

#### **Support du protocole HTTP/2**


<div style="font-size:30px">


#### **Activation de HTTP/2 :**
1. **Vérifier les dépendances :**
   - OpenSSL version 1.0.2 ou supérieure.
   - Apache 2.4.x compilé avec `mod_http2`.

2. **Charger le module `mod_http2` :**
   Ajouter dans le fichier de configuration :
   ```conf
   LoadModule http2_module modules/mod_http2.so
   ```

</div>

---

## Apache HTTPD 2.4 : rappels et nouveautés

#### **Support du protocole HTTP/2**


<div style="font-size:23px">


#### **Activation de HTTP/2 :**
3. **Activer HTTP/2 dans un VirtualHost :**
   Exemple pour un site HTTPS :
   ```conf
   <VirtualHost *:443>
       Protocols h2 http/1.1
       SSLEngine on
       SSLCertificateFile /path/to/certificate.crt
       SSLCertificateKeyFile /path/to/private.key
       DocumentRoot "/var/www/example"
   </VirtualHost>
   ```

4. **Redémarrer Apache :**
   ```bash
   apachectl restart
   ```

</div>

---

## Apache HTTPD 2.4 : rappels et nouveautés

#### **Support du protocole HTTP/2**


<div style="font-size:30px">


#### **Activation de HTTP/2 :**

5. **Vérifier le support de HTTP/2 :**
   - Utilisez un outil comme [curl](https://curl.se/) :
     ```bash
     curl -I --http2 https://example.com
     ```
   - Vérifiez les en-têtes pour `HTTP/2`.

</div>


---

## Apache HTTPD 2.4 : rappels et nouveautés

<br>
<center>
<img src="./assets/practice.png" width="400px">
</center>


---

<!-- _class: lead -->
<!-- _paginate: false -->

## Héberger des applications PHP

---

## Héberger des applications PHP

<br>
<center>
<img src="./assets/practice.png" width="400px">
</center>


---


<!-- _class: lead -->
<!-- _paginate: false -->

## Contrôle d'accès et authentification

---
## Contrôle d'accès et authentification

<br>
<center>
<img src="./assets/practice.png" width="400px">
</center>


---


<!-- _class: lead -->
<!-- _paginate: false -->

## Redirection, réécriture d'adresses, filtres

---

## Redirection, réécriture d'adresses, filtres

<br>
<center>
<img src="./assets/practice.png" width="400px">
</center>


---

<!-- _class: lead -->
<!-- _paginate: false -->

## Reverse Proxy et Cache

---

## Reverse Proxy et Cache

<br>
<center>
<img src="./assets/practice.png" width="400px">
</center>


---

<!-- _class: lead -->
<!-- _paginate: false -->

## Sécuriser les échanges avec HTTPS

---

## Sécuriser les échanges avec HTTPS

<br>
<center>
<img src="./assets/practice.png" width="400px">
</center>


---

<!-- _class: lead -->
<!-- _paginate: false -->

## Sécurité et détection d'attaques


---

## Sécurité et détection d'attaque

<br>
<center>
<img src="./assets/practice.png" width="400px">
</center>


---

<br>
<center>
<img src="./assets/end.jpg" width="800px">
</center>


