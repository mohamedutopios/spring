Voici une démonstration complète d'une API REST pour un modèle `Product` avec une gestion des exceptions personnalisée dans une application Spring Boot.

---

### **Structure du Projet**

1. **Modèle (`Product`)** : Représente un produit.
2. **Service (`ProductService`)** : Contient la logique métier.
3. **Contrôleur (`ProductController`)** : Expose les endpoints REST.
4. **Gestion des exceptions** : Utilise `@RestControllerAdvice` pour gérer les exceptions.

---

### **Code Complet**

#### **1. Modèle `Product`**

Le modèle `Product` inclut des validations simples pour garantir l'intégrité des données.

```java
package org.example.demo.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Product {

    @NotNull(message = "ID is required")
    private Integer id;

    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 0, message = "Price must be positive")
    private Double price;

    public Product() {}

    public Product(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
```

---

#### **2. Service `ProductService`**

Le service simule la logique métier avec une liste en mémoire.

```java
package org.example.demo.service;

import org.example.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    public ProductService() {
        // Initial data
        products.add(new Product(1, "Laptop", 999.99));
        products.add(new Product(2, "Smartphone", 499.99));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Optional<Product> getProductById(int id) {
        return products.stream().filter(product -> product.getId() == id).findFirst();
    }

    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }

    public Product updateProduct(int id, Product updatedProduct) {
        Product existingProduct = getProductById(id).orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        return existingProduct;
    }

    public void deleteProduct(int id) {
        Product product = getProductById(id).orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
        products.remove(product);
    }
}
```

---

#### **3. Exception personnalisée `ProductNotFoundException`**

Créez une exception personnalisée pour signaler qu'un produit n'a pas été trouvé.

```java
package org.example.demo.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
```

---

#### **4. Gestion globale des exceptions**

Utilisez `@RestControllerAdvice` pour capturer et gérer les exceptions.

```java
package org.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFoundException(ProductNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "An unexpected error occurred");
        errorResponse.put("details", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
```

---

#### **5. Contrôleur `ProductController`**

Expose les endpoints REST pour gérer les produits.

```java
package org.example.demo.controller;

import jakarta.validation.Valid;
import org.example.demo.model.Product;
import org.example.demo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET: Retrieve all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // GET: Retrieve product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id).orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
    }

    // POST: Add a new product
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product));
    }

    // PUT: Update a product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @Valid @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    // DELETE: Remove a product
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }
}
```

---

### **Test de l'API**

#### **1. Récupérer tous les produits**
**Requête :**
```
GET /api/products
```

**Réponse :**
```json
[
    {
        "id": 1,
        "name": "Laptop",
        "price": 999.99
    },
    {
        "id": 2,
        "name": "Smartphone",
        "price": 499.99
    }
]
```

---

#### **2. Ajouter un produit valide**
**Requête :**
```json
POST /api/products
{
    "id": 3,
    "name": "Tablet",
    "price": 299.99
}
```

**Réponse :**
```json
{
    "id": 3,
    "name": "Tablet",
    "price": 299.99
}
```

---

#### **3. Ajouter un produit invalide**
**Requête :**
```json
POST /api/products
{
    "id": null,
    "name": "",
    "price": -10
}
```

**Réponse :**
```json
[
    "id: ID is required",
    "name: Name is required",
    "price: Price must be positive"
]
```

---

#### **4. Supprimer un produit inexistant**
**Requête :**
```
DELETE /api/products/10
```

**Réponse (404 Not Found) :**
```json
{
    "error": "Product with ID 10 not found"
}
```

---

### **Résumé**

1. **Exceptions personnalisées (`ProductNotFoundException`)** :
   - Utilisées pour signaler des problèmes spécifiques.
2. **Gestion globale des exceptions (`@RestControllerAdvice`)** :
   - Capture les exceptions pour renvoyer des réponses lisibles.
3. **Validation avec `@Valid` et `BindingResult`** :
   - Gère les erreurs de validation des données.

Ce projet illustre une API REST robuste avec des principes de gestion d'erreurs modernes.