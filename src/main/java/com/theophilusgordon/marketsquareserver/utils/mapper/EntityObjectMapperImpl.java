package com.theophilusgordon.marketsquareserver.utils.mapper;

import com.theophilusgordon.marketsquareserver.dto.*;
import com.theophilusgordon.marketsquareserver.model.*;
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
        ProductDto productDto = new ProductDto();
        List<ProductDto> productDtoList = new ArrayList<>();
        for(Product productEntity : productEntities){
            BeanUtils.copyProperties(productEntity, productDto);
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    @Override
    public OrderDto convertToOrderDto(Order order) {
        return null;
    }

    @Override
    public Order convertToOrderEntity(OrderDto orderDto) {
        return null;
    }

    @Override
    public ReviewDto convertToReviewDto(Review review) {
        return null;
    }

    @Override
    public Review convertToReviewEntity(ReviewDto reviewDto) {
        return null;
    }

    @Override
    public CartDto convertToCartDto(Cart cart) {
        return null;
    }

    @Override
    public Cart convertToCartEntity(CartDto cartDto) {
        return null;
    }

    @Override
    public PaymentDto convertToPaymentDto(Payment payment) {
        return null;
    }

    @Override
    public Payment convertToPaymentEntity(PaymentDto paymentDto) {
        return null;
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
