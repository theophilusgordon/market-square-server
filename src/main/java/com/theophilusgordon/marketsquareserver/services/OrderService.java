package com.theophilusgordon.marketsquareserver.services;

import com.theophilusgordon.marketsquareserver.models.Order;

import java.util.UUID;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrderById(UUID id);
    Order updateOrder(Order order);
    void deleteOrder(UUID id);
}
