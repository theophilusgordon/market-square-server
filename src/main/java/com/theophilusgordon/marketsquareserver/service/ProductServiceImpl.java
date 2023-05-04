package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.dto.ProductDto;
import com.theophilusgordon.marketsquareserver.exception.ProductException;
import com.theophilusgordon.marketsquareserver.model.Product;
import com.theophilusgordon.marketsquareserver.model.User;
import com.theophilusgordon.marketsquareserver.repository.ProductRepository;
import com.theophilusgordon.marketsquareserver.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
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
            .orElseThrow(() -> new ProductException("User not found with id: " + productDto.getSellerId()));
        Product productEntity = new Product();
        BeanUtils.copyProperties(productDto, productEntity);
        productEntity.setSeller(seller);
        productRepository.save(productEntity);
        return productEntity;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(UUID id) {
        return productRepository.findById(id);
    }

    @Override
    public Product updateProduct(UUID id, Product product) {
        Product productEntity = productRepository.findById(id)
            .orElseThrow(() -> new ProductException("Product not found with id: " + id));

        if (product.getName() != null) {
            productEntity.setName(product.getName());
        }
        if (product.getDescription() != null) {
            productEntity.setDescription(product.getDescription());
        }
        if (product.getPrice() != null) {
            productEntity.setPrice(product.getPrice());
        }
        if (product.getImage() != null) {
            productEntity.setImage(product.getImage());
        }
        if (product.getCategory() != null) {
            productEntity.setCategory(product.getCategory());
        }

        return productRepository.save(productEntity);
    }

    @Override
    public void deleteProduct(UUID id) {
        Product productEntity = productRepository.findById(id)
            .orElseThrow(() -> new ProductException("Product not found with id: " + id));
        productRepository.delete(productEntity);
    }
}
