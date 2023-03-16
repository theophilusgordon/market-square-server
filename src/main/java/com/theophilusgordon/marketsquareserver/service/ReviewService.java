package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.dto.ReviewDto;
import com.theophilusgordon.marketsquareserver.model.Review;

import java.util.Optional;
import java.util.UUID;

public interface ReviewService {
    public Review createReview(UUID productId, ReviewDto reviewDto);
    public Optional<Review> getReviewById(UUID id);
    public Review getReviewByProductId(UUID id);
    public Optional<Review> updateReview(UUID reviewId, ReviewDto reviewDto);
    public void deleteReview(UUID id);
}
