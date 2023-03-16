package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.exception.ReviewException;
import com.theophilusgordon.marketsquareserver.model.Review;
import com.theophilusgordon.marketsquareserver.repository.ReviewRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review createReview(UUID productId, Review review) {
        review.setProductId(String.valueOf(productId));
        Review reviewEntity = new Review();

        BeanUtils.copyProperties(review, reviewEntity);
        reviewRepository.save(reviewEntity);
        return reviewEntity;
    }

    @Override
    public Optional<Review> getReviewById(UUID id) {
        return reviewRepository.findById(id);
    }

    @Override
    public Review getReviewByProductId(UUID id) {
        return reviewRepository.findByProductId(id);
    }

    @Override
    public Review updateReview(Review review) {
        boolean reviewExists = reviewRepository.existsById(review.getId());
        if(!reviewExists){
            throw new ReviewException("Review not found with id: " + review.getId());
        }

        Review reviewEntity = reviewRepository.findById(review.getId()).get();
        if(review.getProductId() != null){
            reviewEntity.setProductId(review.getProductId());
        }
        if(review.getRating() != null){
            reviewEntity.setRating(review.getRating());
        }
        if(review.getComment() != null){
            reviewEntity.setComment(review.getComment());
        }
        return reviewEntity;
    }

    @Override
    public void deleteReview(UUID id) {
        reviewRepository.deleteById(id);
    }
}
