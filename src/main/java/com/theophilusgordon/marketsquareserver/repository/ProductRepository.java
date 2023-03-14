package com.theophilusgordon.marketsquareserver.repository;

import com.theophilusgordon.marketsquareserver.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ProductRepository extends JpaRepository<Product, UUID> {
}
