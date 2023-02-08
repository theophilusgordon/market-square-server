package com.theophilusgordon.marketsquareserver.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Order {
    private UUID id;
    private UUID userId;
    private String productId;
    private String quantity;
    private String price;
    private String status;
    private String date;
}
