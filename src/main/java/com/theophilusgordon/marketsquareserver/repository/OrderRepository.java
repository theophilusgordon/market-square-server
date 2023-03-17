package com.theophilusgordon.marketsquareserver.repository;

import com.theophilusgordon.marketsquareserver.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
