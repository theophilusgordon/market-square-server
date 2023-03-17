package com.theophilusgordon.marketsquareserver.repository;

import com.theophilusgordon.marketsquareserver.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
