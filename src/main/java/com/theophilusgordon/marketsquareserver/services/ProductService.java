package com.theophilusgordon.marketsquareserver.services;

import com.theophilusgordon.marketsquareserver.models.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(UUID id);
    Product updateProduct(Product product);
    void deleteProduct(UUID id);
}
