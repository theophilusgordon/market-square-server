package com.theophilusgordon.marketsquareserver.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartDto {
    public String id;
    public String userId;
    public String productId;
    public Integer quantity;
    public BigDecimal totalPrice;
}
