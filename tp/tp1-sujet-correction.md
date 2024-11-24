### **TP : Création d'une API REST pour la gestion des produits**

---

### **Sujet du TP**

Vous devez développer une API REST pour gérer une collection de **produits**. L'API doit inclure les fonctionnalités suivantes :

1. **Créer un produit :**
   - Les données du produit doivent respecter les règles suivantes :
     - `id` : Obligatoire, entier unique.
     - `name` : Obligatoire, chaîne de 3 à 50 caractères.
     - `price` : Obligatoire, positif, ne doit pas dépasser **1000**.
     - `category` : Obligatoire, doit appartenir à une des catégories définies dans une **enum** (`ELECTRONICS`, `BOOKS`, `CLOTHING`).
   - Si le prix dépasse 1000, une exception métier personnalisée **`MaxPriceExceededException`** doit être levée.

2. **Modifier un produit existant :**
   - Les mêmes règles de validation que pour la création s'appliquent.
   - Si l'ID du produit n'existe pas, une erreur doit être renvoyée.

3. **Récupérer un produit par ID :**
   - Si l'ID n'existe pas, une réponse HTTP 404 avec un message personnalisé doit être renvoyée.

4. **Supprimer un produit par ID :**
   - Si l'ID n'existe pas, aucune exception ne doit être levée, mais la suppression doit être ignorée.

5. **Enumération pour `category` :**
   - Les catégories autorisées sont définies dans une enum `Category` : `ELECTRONICS`, `BOOKS`, `CLOTHING`.

6. **Gestion globale des exceptions :**
   - Gestion de l'exception `MaxPriceExceededException` avec une réponse HTTP 400.
   - Gestion de l'exception `IllegalArgumentException` pour les erreurs générales avec une réponse HTTP 400.
   - Gestion des erreurs inattendues avec une réponse HTTP 500.

---

### **Correction**

---

#### **1. Modèle `Product`**

Le modèle inclut la validation des champs et utilise une **enum** pour la catégorie.

```java
package org.example.tp.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Product {

    @NotNull(message = "ID is required")
    private Integer id;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be positive")
    private Double price;

    @NotNull(message = "Category is required")
    private Category category;

    public Product() {}

    public Product(Integer id, String name, Double price, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
```

---

#### **2. Enum `Category`**

Définissez les catégories autorisées pour les produits.

```java
package org.example.tp.model;

public enum Category {
    ELECTRONICS,
    BOOKS,
    CLOTHING
}
```

---

#### **3. Exception personnalisée `MaxPriceExceededException`**

```java
package org.example.tp.exception;

public class MaxPriceExceededException extends RuntimeException {
    public MaxPriceExceededException(String message) {
        super(message);
    }
}
```

---

#### **4. Service `ProductService`**

Ajoutez la logique métier pour valider les prix et gérer les produits en mémoire.

```java
package org.example.tp.service;

import org.example.tp.exception.MaxPriceExceededException;
import org.example.tp.model.Category;
import org.example.tp.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    public ProductService() {
        // Initial sample data
        products.add(new Product(1, "Laptop", 999.99, Category.ELECTRONICS));
        products.add(new Product(2, "Book", 19.99, Category.BOOKS));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null); // Retourne null si non trouvé
    }

    public Product addProduct(Product product) {
        validatePrice(product.getPrice());
        products.add(product);
        return product;
    }

    public Product updateProduct(int id, Product updatedProduct) {
        Product existingProduct = getProductById(id);
        if (existingProduct == null) {
            throw new IllegalArgumentException("Product with ID " + id + " does not exist");
        }
        validatePrice(updatedProduct.getPrice());
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setCategory(updatedProduct.getCategory());
        return existingProduct;
    }

    public void deleteProduct(int id) {
        Product product = getProductById(id);
        if (product != null) {
            products.remove(product);
        }
    }

    private void validatePrice(Double price) {
        if (price > 1000) {
            throw new MaxPriceExceededException("Price cannot exceed 1000. Given price: " + price);
        }
    }
}
```

---

#### **5. Gestion globale des exceptions**

```java
package org.example.tp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MaxPriceExceededException.class)
    public ResponseEntity<Map<String, String>> handleMaxPriceExceededException(MaxPriceExceededException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "An unexpected error occurred");
        response.put("details", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
```

---

#### **6. Contrôleur `ProductController`**

```java
package org.example.tp.controller;

import jakarta.validation.Valid;
import org.example.tp.model.Product;
import org.example.tp.service.ProductService;
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

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with ID " + id + " not found");
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
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

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @Valid @RequestBody Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
   

 @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }
}
```

---

### **Résumé**
1. Modèle `Product` avec une **enum** pour les catégories.
2. Validation métier avec une exception personnalisée **`MaxPriceExceededException`**.
3. Gestion des exceptions globales.
4. Contrôleur REST complet (`GET`, `POST`, `PUT`, `DELETE`).
5. Liste des produits simulée en mémoire.