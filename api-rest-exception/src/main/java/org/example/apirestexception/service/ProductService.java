package org.example.apirestexception.service;
import org.example.apirestexception.exception.ProductNotFoundException;
import org.example.apirestexception.model.Product;
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

