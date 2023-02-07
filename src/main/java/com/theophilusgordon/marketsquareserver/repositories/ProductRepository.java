package com.theophilusgordon.marketsquareserver.repositories;

import com.theophilusgordon.marketsquareserver.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
}
