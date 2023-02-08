package com.theophilusgordon.marketsquareserver.services;

import com.theophilusgordon.marketsquareserver.Exceptions.ProductNotFoundException;
import com.theophilusgordon.marketsquareserver.entities.ProductEntity;
import com.theophilusgordon.marketsquareserver.models.Product;
import com.theophilusgordon.marketsquareserver.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ProductServiceImpl implements  ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public Product createProduct(Product product) {
        ProductEntity productEntity = new ProductEntity();

        BeanUtils.copyProperties(product, productEntity);
        productRepository.save(productEntity);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream().map(productEntity -> {
            Product product = new Product();
            BeanUtils.copyProperties(productEntity, product);
            return product;
        }).toList();
    }

    @Override
    public Product getProductById(UUID id) {
        return productRepository.findById(id).map(productEntity -> {
            Product product = new Product();
            BeanUtils.copyProperties(productEntity, product);
            return product;
        }).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.findById(product.getId()).map(productEntity -> {
            BeanUtils.copyProperties(product, productEntity);
            productRepository.save(productEntity);
            return product;
        }).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + product.getId()));
    }

    @Override
    public void deleteProduct(UUID id) {
        boolean productExists = productRepository.existsById(id);
        if(!productExists){
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
        ResponseEntity.ok(Map.of("message", "User deleted successfully"));
    }
}
