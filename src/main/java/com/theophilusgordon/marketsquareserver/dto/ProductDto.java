package com.theophilusgordon.marketsquareserver.dto;

import com.theophilusgordon.marketsquareserver.model.User;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;

@Data
public class ProductDto {
    public String id;
    @NotNull(message = "Product name is required")
    public String name;
    public String description;
    @NotNull(message = "Price is required")
    public BigDecimal price;
    public String quantity;
    @NotNull(message = "Category is required")
    public String category;
    public String image;
    public String sellerId;
    public User seller;
}
