package com.theophilusgordon.marketsquareserver.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private String name;
    private String description;

    @NotNull
    private String price;
    private String image;

    @NotNull
    private String category;
    private String quantity;

    @NotNull
    private String seller;
}
