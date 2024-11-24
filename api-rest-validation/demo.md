Voici une version simplifiée qui utilise `BindingResult` pour gérer les erreurs de validation sans nécessiter de gestion globale des exceptions. Ce modèle est utile pour renvoyer des messages d'erreur personnalisés au niveau du contrôleur.

---

### **Structure mise à jour**

- **Model (`Contact`)** : Définit les données du contact avec des annotations de validation.
- **Service (`ContactService`)** : Simule la logique métier.
- **Controller (`ContactController`)** : Gère la validation avec `@Valid` et `BindingResult`.

---

### **Code complet**

#### **1. Modèle `Contact` avec validation**

```java
package org.example.validation.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Contact {

    @NotBlank(message = "ID cannot be blank")
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

#### **2. Service `ContactService`**

```java
package org.example.validation.service;

import org.example.validation.model.Contact;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    private final List<Contact> contacts = new ArrayList<>();

    public ContactService() {
        // Initial sample data
        contacts.add(new Contact("1", "John", "Doe", "john.doe@example.com", "1234567890"));
        contacts.add(new Contact("2", "Jane", "Smith", "jane.smith@example.com", "0987654321"));
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public Contact addContact(Contact contact) {
        contacts.add(contact);
        return contact;
    }
}
```

---

#### **3. Contrôleur `ContactController` avec `BindingResult`**

```java
package org.example.validation.controller;

import jakarta.validation.Valid;
import org.example.validation.model.Contact;
import org.example.validation.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // GET: Retrieve all contacts
    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    // POST: Add a new contact with validation
    @PostMapping
    public ResponseEntity<?> addContact(@Valid @RequestBody Contact contact, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Collect all validation errors
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        Contact createdContact = contactService.addContact(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContact);
    }
}
```

---

### **Exemple d'utilisation**

#### **1. Récupérer tous les contacts**
**URL :** `GET /api/contacts`

**Réponse :**
```json
[
    {
        "id": "1",
        "firstName": "John",
        "lastName": "Doe",
        "email": "john.doe@example.com",
        "phone": "1234567890"
    },
    {
        "id": "2",
        "firstName": "Jane",
        "lastName": "Smith",
        "email": "jane.smith@example.com",
        "phone": "0987654321"
    }
]
```

---

#### **2. Ajouter un nouveau contact valide**
**URL :** `POST /api/contacts`

**Corps de la requête :**
```json
{
    "id": "3",
    "firstName": "Alice",
    "lastName": "Brown",
    "email": "alice.brown@example.com",
    "phone": "1122334455"
}
```

**Réponse (201 Created) :**
```json
{
    "id": "3",
    "firstName": "Alice",
    "lastName": "Brown",
    "email": "alice.brown@example.com",
    "phone": "1122334455"
}
```

---

#### **3. Ajouter un contact invalide**
**URL :** `POST /api/contacts`

**Corps de la requête :**
```json
{
    "id": "",
    "firstName": "A",
    "lastName": "",
    "email": "not-an-email",
    "phone": "12345678901234567890"
}
```

**Réponse (400 Bad Request) :**
```json
[
    "id: ID cannot be blank",
    "firstName: First name must be between 2 and 50 characters",
    "lastName: Last name is required",
    "email: Email must be valid",
    "phone: Phone number cannot exceed 15 characters"
]
```

---

### **Explications**

1. **Validation avec `@Valid` :**
   - Utilisé pour activer la validation des champs définis dans le modèle.

2. **`BindingResult` :**
   - Permet de capturer les erreurs de validation.
   - Peut être utilisé pour formater les messages d'erreur de manière personnalisée.

3. **Messages clairs :**
   - Les messages d'erreur sont construits à partir des annotations de validation dans le modèle.

4. **Pas de gestion globale des exceptions :**
   - Les erreurs sont directement gérées dans le contrôleur, sans utiliser `@RestControllerAdvice`.

---

Ce projet met en avant une gestion simple et locale des erreurs de validation, tout en offrant des messages d'erreur lisibles et utiles pour les clients API.