package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.dto.OrderDto;
import com.theophilusgordon.marketsquareserver.exception.OrderException;
import com.theophilusgordon.marketsquareserver.model.Order;
import com.theophilusgordon.marketsquareserver.model.User;
import com.theophilusgordon.marketsquareserver.repository.OrderRepository;
import com.theophilusgordon.marketsquareserver.repository.UserRepository;
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
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Order createOrder(OrderDto orderDto) {
        Order orderEntity = new Order();
        User user = userRepository.findById(UUID.fromString(orderDto.getUserId()))
            .orElseThrow(() -> new OrderException("User not found with id: " + orderDto.getUserId()));

        BeanUtils.copyProperties(orderDto, orderEntity);
        orderRepository.save(orderEntity);
        return orderEntity;
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
