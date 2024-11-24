Les **scopes de bean** définissent la portée et le cycle de vie d'un bean dans un conteneur Spring. Voici un guide avec des exemples pratiques pour démontrer l'intérêt des différents scopes.

---

## **1. Singleton Scope (par défaut)**

- **Description :** Un seul et unique instance de bean est créée et partagée dans tout le conteneur Spring.
- **Utilité :** Idéal pour des objets stateless ou partagés, comme des services ou des composants utilitaires.

### **Exemple**
#### Bean
```java
import org.springframework.stereotype.Component;

@Component
public class SingletonBean {
    public SingletonBean() {
        System.out.println("SingletonBean instance created");
    }
}
```

#### Utilisation
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SingletonScopeExample {

    @Autowired
    private SingletonBean singletonBean1;

    @Autowired
    private SingletonBean singletonBean2;

    public void demonstrateScope() {
        System.out.println("singletonBean1 hashCode: " + singletonBean1.hashCode());
        System.out.println("singletonBean2 hashCode: " + singletonBean2.hashCode());
    }
}
```

#### Résultat
```
SingletonBean instance created
singletonBean1 hashCode: 123456
singletonBean2 hashCode: 123456
```
- **Analyse :** Les deux beans `singletonBean1` et `singletonBean2` partagent la même instance (même hashCode).

---

## **2. Prototype Scope**

- **Description :** Une nouvelle instance du bean est créée à chaque fois qu'il est demandé.
- **Utilité :** Utile pour des objets à courte durée de vie ou ayant un état mutable.

### **Exemple**
#### Bean
```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PrototypeBean {
    public PrototypeBean() {
        System.out.println("PrototypeBean instance created");
    }
}
```

#### Utilisation
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrototypeScopeExample {

    @Autowired
    private PrototypeBean prototypeBean1;

    @Autowired
    private PrototypeBean prototypeBean2;

    public void demonstrateScope() {
        System.out.println("prototypeBean1 hashCode: " + prototypeBean1.hashCode());
        System.out.println("prototypeBean2 hashCode: " + prototypeBean2.hashCode());
    }
}
```

#### Résultat
```
PrototypeBean instance created
PrototypeBean instance created
prototypeBean1 hashCode: 654321
prototypeBean2 hashCode: 987654
```
- **Analyse :** Chaque bean a sa propre instance (différents hashCodes).

---

## **3. Request Scope**

- **Description :** Une instance unique du bean est créée pour chaque requête HTTP.
- **Utilité :** Utile dans les applications web pour des objets spécifiques à une requête, comme des données temporaires.

### **Exemple**
#### Bean
```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class RequestBean {
    public RequestBean() {
        System.out.println("RequestBean instance created");
    }
}
```

#### Contrôleur
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestScopeExample {

    @Autowired
    private RequestBean requestBean;

    @GetMapping("/test-request-scope")
    public String testRequestScope() {
        System.out.println("requestBean hashCode: " + requestBean.hashCode());
        return "Check logs for RequestBean scope!";
    }
}
```

#### Résultat
1. Sur deux requêtes HTTP différentes (`curl localhost:8080/test-request-scope`), vous verrez dans les logs :
   ```
   RequestBean instance created
   requestBean hashCode: 112233
   ```

   Puis pour une seconde requête :
   ```
   RequestBean instance created
   requestBean hashCode: 445566
   ```
- **Analyse :** Une nouvelle instance est créée pour chaque requête HTTP.

---

## **4. Session Scope**

- **Description :** Une instance unique du bean est créée pour chaque session HTTP.
- **Utilité :** Utile pour conserver des données utilisateur au niveau de la session, comme des informations sur le panier.

### **Exemple**
#### Bean
```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class SessionBean {
    public SessionBean() {
        System.out.println("SessionBean instance created");
    }
}
```

#### Contrôleur
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionScopeExample {

    @Autowired
    private SessionBean sessionBean;

    @GetMapping("/test-session-scope")
    public String testSessionScope() {
        System.out.println("sessionBean hashCode: " + sessionBean.hashCode());
        return "Check logs for SessionBean scope!";
    }
}
```

#### Résultat
- Lors de la première requête HTTP d'une session :
  ```
  SessionBean instance created
  sessionBean hashCode: 778899
  ```

- Toutes les requêtes suivantes dans la même session afficheront le même hashCode :
  ```
  sessionBean hashCode: 778899
  ```

- Une nouvelle session génère une nouvelle instance.

---

## **5. Application Scope**

- **Description :** Une instance unique du bean est partagée dans tout le contexte de l'application.
- **Utilité :** Utile pour des données globales ou des caches.

### **Exemple**
#### Bean
```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("application")
public class ApplicationBean {
    public ApplicationBean() {
        System.out.println("ApplicationBean instance created");
    }
}
```

#### Contrôleur
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationScopeExample {

    @Autowired
    private ApplicationBean applicationBean;

    @GetMapping("/test-application-scope")
    public String testApplicationScope() {
        System.out.println("applicationBean hashCode: " + applicationBean.hashCode());
        return "Check logs for ApplicationBean scope!";
    }
}
```

#### Résultat
- Une seule instance est créée pour toute l'application, et ce hashCode reste constant pour toutes les requêtes.

---

## **Récapitulatif des Scopes**

| **Scope**      | **Durée de vie**                               | **Utilisation courante**                                      |
|-----------------|-----------------------------------------------|-------------------------------------------------------------|
| **Singleton**  | Durée de vie de l'application.                | Services, composants utilitaires.                          |
| **Prototype**  | Nouvelle instance à chaque injection.         | Objets à état mutable ou à courte durée de vie.             |
| **Request**    | Une instance par requête HTTP.                | Données spécifiques à une requête (applications web).       |
| **Session**    | Une instance par session HTTP.                | Données utilisateur par session (panier, préférences).      |
| **Application**| Une instance pour toute l'application.        | Données globales ou caches partagés.                       |

---

Avec ces exemples, vous pouvez choisir le scope adapté à vos besoins et mieux comprendre leur impact dans votre application Spring.