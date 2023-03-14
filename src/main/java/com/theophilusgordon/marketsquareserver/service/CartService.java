package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.model.Cart;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartService {
    Cart createCart(Cart cart);
    List<Cart> getAllCarts();
    Optional<Cart> getCartById(UUID id);
    Cart updateCart(Cart cart);
    void deleteCart(UUID id);
}
