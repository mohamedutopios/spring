Créer un **validator personnalisé** dans Spring Boot permet d'appliquer des règles de validation spécifiques qui ne peuvent pas être couvertes par les annotations standard. Voici comment vous pouvez le faire.

---

### **Étapes pour implémenter un validator personnalisé**

1. **Créer une annotation personnalisée.**
2. **Créer une classe de validation qui implémente `ConstraintValidator`.**
3. **Associer l'annotation à la classe de validation.**
4. **Utiliser l'annotation personnalisée dans votre modèle.**

---

### **Exemple complet**

#### **1. Créer une annotation personnalisée**

Créez une nouvelle annotation `@ValidContactId`.

```java
package org.example.validation.custom;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = ContactIdValidator.class) // Associe l'annotation au validateur
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidContactId {
    String message() default "Invalid Contact ID. Must start with 'C-' followed by 3 digits."; // Message par défaut
    Class<?>[] groups() default {}; // Groupes de validation
    Class<? extends Payload>[] payload() default {}; // Charge utile supplémentaire
}
```

---

#### **2. Créer une classe de validation**

Créez une classe `ContactIdValidator` qui contient la logique pour valider les IDs.

```java
package org.example.validation.custom;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ContactIdValidator implements ConstraintValidator<ValidContactId, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false; // ID ne peut pas être vide ou null
        }

        // Vérifie si l'ID correspond au format "C-" suivi de 3 chiffres
        return value.matches("C-\\d{3}");
    }
}
```

---

#### **3. Mise à jour du modèle `Contact`**

Utilisez l'annotation `@ValidContactId` dans le champ `id`.

```java
package org.example.validation.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.validation.custom.ValidContactId;

public class Contact {

    @ValidContactId
    private String id;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @Size(max = 15, message = "Phone number cannot exceed 15 characters")
    private String phone;

    // Constructors
    public Contact() {}

    public Contact(String id, String firstName, String lastName, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
```

---

#### **4. Contrôleur et service**

Le reste du code reste inchangé, mais vous pouvez maintenant tester la validation.

**Exemple du contrôleur :**
```java
@PostMapping
public ResponseEntity<?> addContact(@Valid @RequestBody Contact contact, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
        List<String> errors = bindingResult.getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errors);
    }
    Contact createdContact = contactService.addContact(contact);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdContact);
}
```

---

### **Test de l'API**

#### **1. Ajouter un contact valide**
**Requête :**
```json
{
    "id": "C-123",
    "firstName": "Alice",
    "lastName": "Brown",
    "email": "alice.brown@example.com",
    "phone": "1234567890"
}
```

**Réponse (201 Created) :**
```json
{
    "id": "C-123",
    "firstName": "Alice",
    "lastName": "Brown",
    "email": "alice.brown@example.com",
    "phone": "1234567890"
}
```

---

#### **2. Ajouter un contact invalide**
**Requête :**
```json
{
    "id": "123",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phone": "1234567890"
}
```

**Réponse (400 Bad Request) :**
```json
[
    "id: Invalid Contact ID. Must start with 'C-' followed by 3 digits."
]
```

---

### **Explications**

1. **Annotation personnalisée (`@ValidContactId`) :**
   - Elle est utilisée pour marquer les champs à valider avec une logique personnalisée.

2. **Classe de validation (`ContactIdValidator`) :**
   - Contient la logique pour valider le champ `id` selon le format requis.

3. **Utilisation avec `@Valid` et `BindingResult` :**
   - Permet de collecter les erreurs de validation et de les retourner sous forme lisible.

---

Avec ce validator personnalisé, vous pouvez valider n'importe quel champ de manière spécifique, tout en respectant le framework de validation de Spring Boot.