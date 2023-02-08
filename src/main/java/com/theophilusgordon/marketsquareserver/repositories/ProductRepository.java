package com.theophilusgordon.marketsquareserver.repositories;

import com.theophilusgordon.marketsquareserver.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ProductRepository extends JpaRepository<ProductEntity, UUID>{
}
