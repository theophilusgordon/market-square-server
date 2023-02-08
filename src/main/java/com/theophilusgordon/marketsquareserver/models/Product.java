package com.theophilusgordon.marketsquareserver.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Product {
    private UUID id;
    private String name;
    private String description;
    private String price;
    private String image;
    private String category;
    private String quantity;
    private String seller;
}
