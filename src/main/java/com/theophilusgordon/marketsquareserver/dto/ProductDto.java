package com.theophilusgordon.marketsquareserver.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
public class ProductDto {
    public String id;
    @NotNull
    public String name;
    public String description;
    @NotNull
    public BigDecimal price;
    public String quantity;
    @NotNull
    public String category;
    public String image;
    public String sellerId;
}
