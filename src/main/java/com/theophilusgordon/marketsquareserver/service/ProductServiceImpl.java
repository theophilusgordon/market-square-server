package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.dto.ProductDto;
import com.theophilusgordon.marketsquareserver.exception.ProductException;
import com.theophilusgordon.marketsquareserver.model.Product;
import com.theophilusgordon.marketsquareserver.model.User;
import com.theophilusgordon.marketsquareserver.repository.ProductRepository;
import com.theophilusgordon.marketsquareserver.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }
    @Override
    public Product createProduct(ProductDto productDto) {
        User seller = userRepository.findById(UUID.fromString(productDto.getSellerId()))
            .orElseThrow(() -> new ProductException("User not found with id: " + productDto.getSeller().getId()));
        Product productEntity = new Product();
        BeanUtils.copyProperties(productDto, productEntity);
        productEntity.setSeller(seller);
        productRepository.save(productEntity);
        return productEntity;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productEntities = productRepository.findAll();
        return productEntities.stream().map(productEntity -> {
            Product product = new Product();
            BeanUtils.copyProperties(productEntity, product);
            return product;
        }).toList();
    }

    @Override
    public Optional<Product> getProductById(UUID id) {
        return productRepository.findById(id);
    }

    @Override
    public Product updateProduct(Product product) {
        boolean productExists = productRepository.existsById(product.getId());
        if(!productExists){
            throw new ProductException("Product not found with id: " + product.getId());
        }

        Product productEntity = productRepository.findById(product.getId()).get();
        if(product.getName() != null){
            productEntity.setName(product.getName());
        }
        if(product.getDescription() != null){
            productEntity.setDescription(product.getDescription());
        }
        if(product.getPrice() != null){
            productEntity.setPrice(product.getPrice());
        }

        if(product.getImage() != null){
            productEntity.setImage(product.getImage());
        }

        if(product.getCategory() != null){
            productEntity.setCategory(product.getCategory());
        }

        return productRepository.save(productEntity);
    }

    @Override
    public void deleteProduct(UUID id) {
        boolean productExists = productRepository.existsById(id);
        if(!productExists){
            throw new ProductException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
        ResponseEntity.ok(Map.of("message", "User deleted successfully"));
    }
}
