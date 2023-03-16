package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.exception.OrderException;
import com.theophilusgordon.marketsquareserver.model.Order;
import com.theophilusgordon.marketsquareserver.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        Order orderEntity = new Order();

        BeanUtils.copyProperties(order, orderEntity);
        orderRepository.save(orderEntity);
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orderEntities = (List<Order>) orderRepository.findAll();
        return orderEntities.stream().map(orderEntity -> {
            Order order = new Order();
            BeanUtils.copyProperties(orderEntity, order);
            return order;
        }).toList();
    }

    @Override
    public Optional<Order> getOrderById(UUID id) {
        return Optional.ofNullable(orderRepository.findById(id).map(orderEntity -> {
            Order order = new Order();
            BeanUtils.copyProperties(orderEntity, order);
            return order;
        }).orElseThrow(() -> new OrderException("Order not found with id: " + id)));
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.findById(order.getId()).map(orderEntity -> {
            BeanUtils.copyProperties(order, orderEntity);
            orderRepository.save(orderEntity);
            return order;
        }).orElseThrow(() -> new OrderException("Order not found with id: " + order.getId()));
    }

    @Override
    public void deleteOrder(UUID id) {
        boolean orderExists = orderRepository.existsById(id);
        if(!orderExists){
            throw new OrderException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
        ResponseEntity.ok(Map.of("message", "Order deleted successfully!"));
    }
}
