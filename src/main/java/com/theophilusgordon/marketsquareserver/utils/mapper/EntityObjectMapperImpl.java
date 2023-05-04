package com.theophilusgordon.marketsquareserver.utils.mapper;

import com.theophilusgordon.marketsquareserver.dto.*;
import com.theophilusgordon.marketsquareserver.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EntityObjectMapperImpl implements EntityObjectMapper{
    @Override
    public UserDto convertToUserDto(User user) {
        return setUserDto(user);
    }

    @Override
    public Optional<UserDto> convertToUserDtoOptional(User user) {
        return Optional.of(setUserDto(user));
    }

    @Override
    public User convertToUserEntity(UserDto userDto) {
        User user = new User();
        user.setId(UUID.fromString(userDto.getId()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAddress(userDto.getAddress());
        user.setCity(userDto.getCity());
        user.setState(userDto.getState());
        user.setCountry(userDto.getCountry());
        return user;
    }

    @Override
    public ProductDto convertToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    @Override
    public Product convertToProductEntity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }

    @Override
    public List<ProductDto> convertToProductDtoList(List<Product> productEntities) {
        List<ProductDto> productDtoList = new ArrayList<>();
        for(Product productEntity : productEntities){
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(productEntity, productDto);
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    @Override
    public OrderDto convertToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(order, orderDto);
        return orderDto;
    }

    @Override
    public Order convertToOrderEntity(OrderDto orderDto) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDto, order);
        return order;
    }

    @Override
    public ReviewDto convertToReviewDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        BeanUtils.copyProperties(review, reviewDto);
        return reviewDto;
    }

    @Override
    public Review convertToReviewEntity(ReviewDto reviewDto) {
        Review review = new Review();
        BeanUtils.copyProperties(reviewDto, review);
        return review;
    }

    @Override
    public CartDto convertToCartDto(Cart cart) {
        CartDto cartDto = new CartDto();
        BeanUtils.copyProperties(cart, cartDto);
        return cartDto;
    }

    @Override
    public Cart convertToCartEntity(CartDto cartDto) {
        Cart cart = new Cart();
        BeanUtils.copyProperties(cartDto, cart);
        return cart;
    }

    @Override
    public PaymentDto convertToPaymentDto(Payment payment) {
        PaymentDto paymentDto = new PaymentDto();
        BeanUtils.copyProperties(payment, paymentDto);
        return paymentDto;
    }

    @Override
    public Payment convertToPaymentEntity(PaymentDto paymentDto) {
        Payment payment = new Payment();
        BeanUtils.copyProperties(paymentDto, payment);
        return payment;
    }

    @Override
    public List<UserDto> convertToUserDtoList(List<User> userEntities) {
        List<UserDto> userDtoList = new ArrayList<>();
        for(User userEntity : userEntities){
            UserDto user = setUserDto(userEntity);
            userDtoList.add(user);
        }
        return userDtoList;
    }

    private UserDto setUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(String.valueOf(user.getId()));
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setEmail(user.getEmail());
        userDto.setAddress(user.getAddress());
        userDto.setCity(user.getCity());
        userDto.setState(user.getState());
        userDto.setCountry(user.getCountry());
        return userDto;
    }
}
