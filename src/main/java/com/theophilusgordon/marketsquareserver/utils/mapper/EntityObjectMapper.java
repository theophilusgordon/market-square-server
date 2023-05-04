package com.theophilusgordon.marketsquareserver.utils.mapper;

import com.theophilusgordon.marketsquareserver.dto.*;
import com.theophilusgordon.marketsquareserver.entity.*;

import java.util.List;
import java.util.Optional;

public interface EntityObjectMapper {
    UserDto convertToUserDto(User user);
    Optional<UserDto> convertToUserDtoOptional(User user);
    List<UserDto> convertToUserDtoList(List<User> userEntities);
    User convertToUserEntity(UserDto userDto);

    ProductDto convertToProductDto(Product product);
    Product convertToProductEntity(ProductDto productDto);
    List<ProductDto> convertToProductDtoList(List<Product> productEntities);

    OrderDto convertToOrderDto(Order order);
    Order convertToOrderEntity(OrderDto orderDto);

    ReviewDto convertToReviewDto(Review review);
    Review convertToReviewEntity(ReviewDto reviewDto);

    CartDto convertToCartDto(Cart cart);
    Cart convertToCartEntity(CartDto cartDto);

    PaymentDto convertToPaymentDto(Payment payment);
    Payment convertToPaymentEntity(PaymentDto paymentDto);

}
