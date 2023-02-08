package com.theophilusgordon.marketsquareserver.repositories;

import com.theophilusgordon.marketsquareserver.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
}
