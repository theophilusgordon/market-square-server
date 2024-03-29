package com.theophilusgordon.marketsquareserver.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewDto {
    @NotNull
    public String id;
    public String reviewerId;
    public String productId;
    public String comment;
    public Float rating;
}
