package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.model.Cart;
import com.theophilusgordon.marketsquareserver.repository.CartRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart createCart(Cart cart) {
        Cart cartEntity = new Cart();

        BeanUtils.copyProperties(cart, cartEntity);
        cartRepository.save(cartEntity);
        return cart;
    }

    @Override
    public List<Cart> getAllCarts() {
        List<Cart> cartList = (List<Cart>) cartRepository.findAll();
        return cartList.stream().map(cartEntity -> {
            Cart cart = new Cart();
            BeanUtils.copyProperties(cartEntity, cart);
            return cart;
        }).toList();
    }

    @Override
    public Optional<Cart> getCartById(UUID id) {
        return Optional.ofNullable(cartRepository.findById(id).map(cartEntity -> {
            Cart cart = new Cart();
            BeanUtils.copyProperties(cartEntity, cart);
            return cart;
        }).orElseThrow(() -> new RuntimeException("Cart not found with id: " + id)));
    }

    @Override
    public Cart updateCart(Cart cart) {
        return cartRepository.findById(cart.getId()).map(cartEntity -> {
            BeanUtils.copyProperties(cart, cartEntity);
            cartRepository.save(cartEntity);
            return cart;
        }).orElseThrow(() -> new RuntimeException("Cart not found with id: " + cart.getId()));
    }

    @Override
    public void deleteCart(UUID id) {
        boolean cartExists = cartRepository.existsById(id);
        if(!cartExists){
            throw new RuntimeException("Cart not found with id: " + id);
        }
        cartRepository.deleteById(id);
    }
}
