package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.model.Review;

import java.util.Optional;
import java.util.UUID;

public interface ReviewService {
    public Review createReview(UUID productId, Review review);
    public Optional<Review> getReviewById(UUID id);
    public Review getReviewByProductId(UUID id);
    public Review updateReview(Review review);
    public void deleteReview(UUID id);
}
