package com.theophilusgordon.marketsquareserver.dto;

import lombok.Data;

@Data
public class OrderDto {
    public String id;
    public String userId;
    public String productId;
    public Integer quantity;
    public String status;
}
