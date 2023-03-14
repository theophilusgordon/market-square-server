package com.theophilusgordon.marketsquareserver.controller;

import com.theophilusgordon.marketsquareserver.model.Order;
import com.theophilusgordon.marketsquareserver.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("")
    public Order createOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrderById(@PathVariable UUID id){
        return orderService.getOrderById(id);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable UUID id, @RequestBody Order order){
        order.setId(id);
        return orderService.updateOrder(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable UUID id){
        orderService.deleteOrder(id);
    }
}
