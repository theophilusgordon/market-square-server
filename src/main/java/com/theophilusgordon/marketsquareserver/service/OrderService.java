package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.dto.OrderDto;
import com.theophilusgordon.marketsquareserver.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    Order createOrder(OrderDto orderDto);
    List<Order> getAllOrders();
    Optional<Order> getOrderById(UUID id);
    Order updateOrder(Order order);
    void deleteOrder(UUID id);
}
