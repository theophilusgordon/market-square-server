package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.dto.CartDto;
import com.theophilusgordon.marketsquareserver.entity.Cart;
import com.theophilusgordon.marketsquareserver.entity.Product;
import com.theophilusgordon.marketsquareserver.entity.User;
import com.theophilusgordon.marketsquareserver.repository.CartRepository;
import com.theophilusgordon.marketsquareserver.repository.ProductRepository;
import com.theophilusgordon.marketsquareserver.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository,
                           UserRepository userRepository,
                           ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Cart createCart(CartDto cartDto) {
        User cartUser = userRepository.findById(UUID.fromString(cartDto.getUserId()))
                .orElseThrow(() -> new RuntimeException("User not found with id: " + cartDto.getUserId()));
        Product cartProduct = productRepository.findById(UUID.fromString(cartDto.getProductId()))
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + cartDto.getProductId()));
        Cart cartEntity = new Cart();
        BeanUtils.copyProperties(cartDto, cartEntity);
        cartEntity.setUser(cartUser);
        cartEntity.setProducts(List.of(cartProduct));
        BigDecimal totalPrice = cartProduct.getPrice().multiply(BigDecimal.valueOf(cartDto.getQuantity()));
        cartEntity.setTotalPrice(totalPrice);
        cartRepository.save(cartEntity);
        return cartEntity;
    }

    @Override
    public List<Cart> getAllCarts() {
        List<Cart> cartList = cartRepository.findAll();
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
    public Cart updateCart(UUID id, CartDto cartDto) {
        boolean cartExists = cartRepository.existsById(id);
        if(!cartExists){
            throw new RuntimeException("Cart not found with id: " + id);
        }

        if(cartDto.getQuantity() != null && cartDto.getQuantity() > 0){
        Cart cartEntity = cartRepository.findById(id).get();
            Product cartProduct = cartEntity.getProducts().get(0);
            cartEntity.setQuantity(cartDto.getQuantity());
            BigDecimal totalPrice = cartProduct.getPrice().multiply(BigDecimal.valueOf(cartDto.getQuantity()));
            cartEntity.setTotalPrice(totalPrice);
            cartRepository.save(cartEntity);
            return cartEntity;
        } else {
            throw new RuntimeException("Quantity must be greater than 0");
        }
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
