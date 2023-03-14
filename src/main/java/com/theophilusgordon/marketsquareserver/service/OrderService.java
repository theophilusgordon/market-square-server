package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    Order createOrder(Order order);
    List<Order> getAllOrders();
    Optional<Order> getOrderById(UUID id);
    Order updateOrder(Order order);
    void deleteOrder(UUID id);
}
