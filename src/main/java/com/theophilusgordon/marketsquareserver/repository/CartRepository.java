package com.theophilusgordon.marketsquareserver.repository;

import com.theophilusgordon.marketsquareserver.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
}
