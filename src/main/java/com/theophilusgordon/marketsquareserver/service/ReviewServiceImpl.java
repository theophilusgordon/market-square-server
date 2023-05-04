package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.dto.ReviewDto;
import com.theophilusgordon.marketsquareserver.exception.ProductException;
import com.theophilusgordon.marketsquareserver.exception.ReviewException;
import com.theophilusgordon.marketsquareserver.entity.Product;
import com.theophilusgordon.marketsquareserver.entity.Review;
import com.theophilusgordon.marketsquareserver.entity.User;
import com.theophilusgordon.marketsquareserver.repository.ProductRepository;
import com.theophilusgordon.marketsquareserver.repository.ReviewRepository;
import com.theophilusgordon.marketsquareserver.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Review createReview(ReviewDto reviewDto) {
        User reviewer = userRepository.findById(UUID.fromString(reviewDto.getReviewerId()))
            .orElseThrow(() -> new ProductException("User not found with id: " + reviewDto.getReviewerId()));

        Product product = productRepository.findById(UUID.fromString(reviewDto.getProductId()))
            .orElseThrow(() -> new ProductException("Product not found with id: " + reviewDto.getProductId()));
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
    public List<Review> getReviewsByProductId(UUID id) {
        boolean productExists = productRepository.existsById(id);
        if(!productExists){
            throw new ProductException("Product not found with id: " + id);
        }

        return reviewRepository.findAllByProductId(id);
    }


    @Override
    public Optional<Review> updateReview(UUID reviewId, ReviewDto reviewDto) {
        boolean reviewExists = reviewRepository.existsById(reviewId);
        if(!reviewExists){
            throw new ReviewException("Review not found with id: " + reviewDto.getId());
        }

        Optional<Review> reviewEntity = reviewRepository.findById(reviewId);

        reviewEntity.ifPresent(review -> {
            if(reviewDto.getRating() != null) review.setRating(reviewDto.getRating());
            if(reviewDto.getComment() != null) review.setComment(reviewDto.getComment());
            reviewRepository.save(review);
        });

        return reviewEntity;
    }

    @Override
    public void deleteReview(UUID id) {
        reviewRepository.deleteById(id);
    }
}
