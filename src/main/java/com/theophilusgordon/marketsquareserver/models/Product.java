package com.theophilusgordon.marketsquareserver.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String name;
    private String description;
    private String price;
    private String image;
    private String category;
    private String quantity;
    private String seller;
}
