package com.theophilusgordon.marketsquareserver.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity(name = "reviews")
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "reviewer_id", referencedColumnName = "id")
    private User reviewer;
    private String comment;
    private String rating;
}
