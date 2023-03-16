package com.theophilusgordon.marketsquareserver.utils.mapper;

import com.theophilusgordon.marketsquareserver.dto.*;
import com.theophilusgordon.marketsquareserver.model.*;

import java.util.List;
import java.util.Optional;

public interface EntityObjectMapper {
    UserDto convertToUserDto(User user);
    Optional<UserDto> convertToUserDtoOptional(User user);
    User convertToUser(UserDto userDto);

    ProductDto convertToProductDto(Product product);
    Product convertToProduct(ProductDto productDto);

    OrderDto convertToOrderDto(Order order);
    Order convertToOrder(OrderDto orderDto);

    ReviewDto convertToReviewDto(Review review);
    Review convertToReview(ReviewDto reviewDto);

    CartDto convertToCartDto(Cart cart);
    Cart convertToCart(CartDto cartDto);

    PaymentDto convertToPaymentDto(Payment payment);
    Payment convertToPayment(PaymentDto paymentDto);

    List<UserDto> convertToUserDtoList(List<User> userEntities);
}
