Pour implémenter une validation par **groupes conditionnels** dans Spring Boot, vous pouvez utiliser l'annotation `@Validated` en combinaison avec des groupes définis pour des validations conditionnelles. Voici une version complète basée sur votre modèle `Contact`.

---

### **Étapes pour la validation par groupes conditionnels**

1. **Créer des interfaces pour représenter les groupes de validation.**
2. **Associer des groupes à des contraintes dans le modèle.**
3. **Utiliser `@Validated` et spécifier le groupe lors de la validation.**

---

### **Code complet avec validation par groupes conditionnels**

#### **1. Définir les groupes de validation**

Créez des interfaces pour représenter les groupes. Par exemple, un groupe pour la création (`CreateGroup`) et un autre pour la mise à jour (`UpdateGroup`).

```java
package org.example.validation.groups;

public interface CreateGroup {}

public interface UpdateGroup {}
```

---

#### **2. Mise à jour du modèle `Contact`**

Utilisez les interfaces des groupes pour associer des contraintes de validation.

```java
package org.example.validation.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.example.validation.groups.CreateGroup;
import org.example.validation.groups.UpdateGroup;

public class Contact {

    @NotBlank(groups = {CreateGroup.class}, message = "ID cannot be blank")
    @Pattern(regexp = "[A-Z]{3}[0-9]{3}", groups = {CreateGroup.class, UpdateGroup.class}, message = "ID must follow the pattern: 3 uppercase letters followed by 3 digits (e.g., ABC123)")
    private String id;

    @NotBlank(groups = {CreateGroup.class, UpdateGroup.class}, message = "First name is required")
    @Size(min = 2, max = 50, groups = {CreateGroup.class, UpdateGroup.class}, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(groups = {CreateGroup.class, UpdateGroup.class}, message = "Last name is required")
    @Size(min = 2, max = 50, groups = {CreateGroup.class, UpdateGroup.class}, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(groups = {CreateGroup.class, UpdateGroup.class}, message = "Email is required")
    @Email(groups = {CreateGroup.class, UpdateGroup.class}, message = "Email must be valid")
    private String email;

    @Pattern(regexp = "\\d{10}", groups = {CreateGroup.class, UpdateGroup.class}, message = "Phone number must be exactly 10 digits")
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

#### **3. Mise à jour du service**

Le service reste inchangé, il contient la logique métier pour gérer les contacts.

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
        contacts.add(new Contact("ABC123", "John", "Doe", "john.doe@example.com", "1234567890"));
        contacts.add(new Contact("DEF456", "Jane", "Smith", "jane.smith@example.com", "0987654321"));
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public Contact addContact(Contact contact) {
        contacts.add(contact);
        return contact;
    }

    public Contact updateContact(String id, Contact updatedContact) {
        Contact existingContact = contacts.stream()
                .filter(contact -> contact.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Contact not found"));
        existingContact.setFirstName(updatedContact.getFirstName());
        existingContact.setLastName(updatedContact.getLastName());
        existingContact.setEmail(updatedContact.getEmail());
        existingContact.setPhone(updatedContact.getPhone());
        return existingContact;
    }
}
```

---

#### **4. Mise à jour du contrôleur**

Ajoutez des endpoints distincts pour la création et la mise à jour. Utilisez `@Validated` pour appliquer les groupes appropriés.

```java
package org.example.validation.controller;

import jakarta.validation.Valid;
import org.example.validation.groups.CreateGroup;
import org.example.validation.groups.UpdateGroup;
import org.example.validation.model.Contact;
import org.example.validation.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    // POST: Add a new contact (validation for CreateGroup)
    @PostMapping
    public ResponseEntity<?> addContact(@Validated(CreateGroup.class) @RequestBody Contact contact, BindingResult bindingResult) {
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

    // PUT: Update a contact (validation for UpdateGroup)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(
            @PathVariable String id,
            @Validated(UpdateGroup.class) @RequestBody Contact contact,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        Contact updatedContact = contactService.updateContact(id, contact);
        return ResponseEntity.ok(updatedContact);
    }
}
```

---

### **Exemples d'utilisation**

#### **1. Créer un contact valide (POST /api/contacts)**
**Requête :**
```json
{
    "id": "XYZ789",
    "firstName": "Alice",
    "lastName": "Brown",
    "email": "alice.brown@example.com",
    "phone": "1234567890"
}
```

**Réponse (201 Created) :**
```json
{
    "id": "XYZ789",
    "firstName": "Alice",
    "lastName": "Brown",
    "email": "alice.brown@example.com",
    "phone": "1234567890"
}
```

---

#### **2. Créer un contact invalide (POST /api/contacts)**
**Requête :**
```json
{
    "id": "",
    "firstName": "",
    "lastName": "",
    "email": "not-an-email",
    "phone": "123"
}
```

**Réponse (400 Bad Request) :**
```json
[
    "id: ID cannot be blank",
    "firstName: First name is required",
    "lastName: Last name is required",
    "email: Email must be valid",
    "phone: Phone number must be exactly 10 digits"
]
```

---

#### **3. Mettre à jour un contact valide (PUT /api/contacts/{id})**
**Requête :**
```json
{
    "id": "DEF456",
    "firstName": "Jane",
    "lastName": "Doe",
    "email": "jane.doe@example.com",
    "phone": "0987654321"
}
```

**Réponse (200 OK) :**
```json
{
    "id": "DEF456",
    "firstName": "Jane",
    "lastName": "Doe",
    "email": "jane.doe@example.com",
    "phone": "0987654321"
}
```

---

### **Résumé**
- **Groupes de validation** permettent d’appliquer des contraintes spécifiques selon le contexte (création ou mise à jour).
- Utilisez `@

Validated` pour spécifier le groupe de validation.
- Gestion des erreurs simple avec `BindingResult`.

Cette approche offre une validation flexible et contextuelle pour vos endpoints REST.