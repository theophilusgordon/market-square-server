package com.theophilusgordon.marketsquareserver.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    private String name;
    private String description;
    @NotNull
    private BigDecimal price;
    private String image;
    @NotNull
    private String category;
    @ManyToOne
//    @JoinColumn(name = "user_id")
    private User seller;
}
