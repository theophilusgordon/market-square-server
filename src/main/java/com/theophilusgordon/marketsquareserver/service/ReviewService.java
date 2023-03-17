package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.dto.ReviewDto;
import com.theophilusgordon.marketsquareserver.model.Review;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewService {
    public Review createReview(ReviewDto reviewDto);
    public Optional<Review> getReviewById(UUID id);
    public List<Review> getReviewsByProductId(UUID id);
    public Optional<Review> updateReview(UUID reviewId, ReviewDto reviewDto);
    public void deleteReview(UUID id);
}
