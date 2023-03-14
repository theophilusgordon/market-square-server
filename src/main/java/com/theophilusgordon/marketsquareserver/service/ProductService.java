package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts();
    Optional<Product> getProductById(UUID id);
    Product updateProduct(Product product);
    void deleteProduct(UUID id);
}
