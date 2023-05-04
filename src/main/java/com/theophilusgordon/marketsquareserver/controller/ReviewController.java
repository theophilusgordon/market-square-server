package com.theophilusgordon.marketsquareserver.controller;

import com.theophilusgordon.marketsquareserver.dto.ReviewDto;
import com.theophilusgordon.marketsquareserver.entity.Review;
import com.theophilusgordon.marketsquareserver.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<Review> createReview(@RequestBody ReviewDto reviewDto){
        Review createdReview = reviewService.createReview(reviewDto);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable UUID id){
        Optional<Review> review = reviewService.getReviewById(id);
        return review.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("product/{id}")
    public ResponseEntity<List<Review>> getReviewByProductId(@PathVariable UUID id){
        List<Review> reviews = reviewService.getReviewsByProductId(id);
        return ResponseEntity.ok(reviews);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<Review> updateReview(@PathVariable UUID id, @RequestBody ReviewDto reviewDto){
        Optional<Review> updatedReview = reviewService.updateReview(id, reviewDto);
        return updatedReview.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<Void> deleteReview(@PathVariable UUID id){
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
