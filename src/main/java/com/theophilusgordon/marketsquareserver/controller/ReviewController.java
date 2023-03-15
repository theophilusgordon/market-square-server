package com.theophilusgordon.marketsquareserver.controller;

import com.theophilusgordon.marketsquareserver.model.Review;
import com.theophilusgordon.marketsquareserver.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{productId}")
    public Review createReview(@PathVariable UUID productId, @RequestBody Review review){
        return reviewService.createReview(productId, review);
    }

    @GetMapping("/{id}")
    public Optional<Review> getReviewById(@PathVariable UUID id){
        return reviewService.getReviewById(id);
    }

    @GetMapping("product/{id}")
    public Review getReviewByProductId(@PathVariable UUID id){
        return reviewService.getReviewByProductId(id);
    }

    @PutMapping("/{id}")
    public Review updateReview(@PathVariable UUID id, @RequestBody Review review){
        return reviewService.updateReview(review);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable UUID id){
        reviewService.deleteReview(id);
    }
}
