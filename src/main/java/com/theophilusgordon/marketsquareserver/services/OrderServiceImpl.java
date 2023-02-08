package com.theophilusgordon.marketsquareserver.services;

import com.theophilusgordon.marketsquareserver.Exceptions.OrderNotFoundException;
import com.theophilusgordon.marketsquareserver.entities.OrderEntity;
import com.theophilusgordon.marketsquareserver.models.Order;
import com.theophilusgordon.marketsquareserver.repositories.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        OrderEntity orderEntity = new OrderEntity();

        BeanUtils.copyProperties(order, orderEntity);
        orderRepository.save(orderEntity);
        return order;
    }

    @Override
    public Order getOrderById(UUID id) {
        return orderRepository.findById(id).map(orderEntity -> {
            Order order = new Order();
            BeanUtils.copyProperties(orderEntity, order);
            return order;
        }).orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.findById(order.getId()).map(orderEntity -> {
            BeanUtils.copyProperties(order, orderEntity);
            orderRepository.save(orderEntity);
            return order;
        }).orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + order.getId()));
    }

    @Override
    public void deleteOrder(UUID id) {
        boolean orderExists = orderRepository.existsById(id);
        if(!orderExists){
            throw new OrderNotFoundException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
        ResponseEntity.ok(Map.of("message", "Order deleted successfully!"));
    }
}
