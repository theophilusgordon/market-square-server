package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.dto.ProductDto;
import com.theophilusgordon.marketsquareserver.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    Product createProduct(ProductDto productDto);
    List<Product> getAllProducts();
    Optional<Product> getProductById(UUID id);
    Product updateProduct(UUID id, Product product);
    void deleteProduct(UUID id);
}
