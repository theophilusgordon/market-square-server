package com.theophilusgordon.marketsquareserver.controller;

import com.theophilusgordon.marketsquareserver.dto.CartDto;
import com.theophilusgordon.marketsquareserver.model.Cart;
import com.theophilusgordon.marketsquareserver.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("")
    public Cart createCart(@RequestBody CartDto cartDto){
        return cartService.createCart(cartDto);
    }

    @GetMapping("")
    public List<Cart> getAllCarts(){
        return cartService.getAllCarts();
    }

    @GetMapping("/{id}")
    public Optional<Cart> getCartById(@PathVariable UUID id){
        return cartService.getCartById(id);
    }

    @PutMapping("/{id}")
    public Cart updateCart(@PathVariable UUID id, @RequestBody CartDto cartDto){
        return cartService.updateCart(id, cartDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable UUID id){
        cartService.deleteCart(id);
    }
}
