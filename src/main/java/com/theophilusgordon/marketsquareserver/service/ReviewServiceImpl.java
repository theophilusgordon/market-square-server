package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.dto.ReviewDto;
import com.theophilusgordon.marketsquareserver.exception.ProductException;
import com.theophilusgordon.marketsquareserver.exception.ReviewException;
import com.theophilusgordon.marketsquareserver.model.Product;
import com.theophilusgordon.marketsquareserver.model.Review;
import com.theophilusgordon.marketsquareserver.model.User;
import com.theophilusgordon.marketsquareserver.repository.ProductRepository;
import com.theophilusgordon.marketsquareserver.repository.ReviewRepository;
import com.theophilusgordon.marketsquareserver.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Review createReview(UUID productId, ReviewDto reviewDto) {
        User reviewer = userRepository.findById(UUID.fromString(reviewDto.getReviewerId()))
            .orElseThrow(() -> new ProductException("User not found with id: " + productId));

        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ProductException("User not found with id: " + productId));
        Review reviewEntity = new Review();

        BeanUtils.copyProperties(reviewDto, reviewEntity);
        reviewEntity.setReviewer(reviewer);
        reviewEntity.setProduct(product);
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
    public Optional<Review> updateReview(UUID reviewId, ReviewDto reviewDto) {
        boolean reviewExists = reviewRepository.existsById(reviewId);
        if(!reviewExists){
            throw new ReviewException("Review not found with id: " + reviewDto.getId());
        }

        Optional<Review> reviewEntity = reviewRepository.findById(reviewId);

//        if(reviewDto.getReviewerId() != null){
//            User reviewer = userRepository.findById(UUID.fromString(reviewDto.getReviewerId()))
//                .orElseThrow(() -> new ProductException("User not found with id: " + reviewDto.getReviewerId()));
//            reviewEntity.setReviewer(reviewer);
//        }
//        if(reviewDto.getRating() != null){
//            reviewEntity.setRating(reviewDto.getRating());
//        }
//        if(reviewDto.getReview() != null){
//            reviewEntity.setComment(reviewDto.getReview());
//        }
        return reviewEntity;
    }

    @Override
    public void deleteReview(UUID id) {
        reviewRepository.deleteById(id);
    }
}
