package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.dto.CartDto;
import com.theophilusgordon.marketsquareserver.model.Cart;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartService {
    Cart createCart(CartDto cartDto);
    List<Cart> getAllCarts();
    Optional<Cart> getCartById(UUID id);
    Cart updateCart(UUID id, CartDto cartDto);
    void deleteCart(UUID id);
}
