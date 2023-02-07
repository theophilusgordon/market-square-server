package com.theophilusgordon.marketsquareserver.services;

import com.theophilusgordon.marketsquareserver.entities.ProductEntity;
import com.theophilusgordon.marketsquareserver.models.Product;
import com.theophilusgordon.marketsquareserver.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        return;
    }
}
