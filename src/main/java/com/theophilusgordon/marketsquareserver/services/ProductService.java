package com.theophilusgordon.marketsquareserver.services;

import com.theophilusgordon.marketsquareserver.models.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product updateProduct(Product product);
    void deleteProduct(Long id);
}
