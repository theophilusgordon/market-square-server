package com.theophilusgordon.marketsquareserver.controller;

import com.theophilusgordon.marketsquareserver.dto.ReviewDto;
import com.theophilusgordon.marketsquareserver.model.Review;
import com.theophilusgordon.marketsquareserver.service.ReviewService;
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

    @PostMapping("")
    public Review createReview(@RequestBody ReviewDto reviewDto){
        return reviewService.createReview(reviewDto);
    }

    @GetMapping("/{id}")
    public Optional<Review> getReviewById(@PathVariable UUID id){
        return reviewService.getReviewById(id);
    }

    @GetMapping("product/{id}")
    public List<Review> getReviewByProductId(@PathVariable UUID id){
        return reviewService.getReviewsByProductId(id);
    }

    @PutMapping("/{id}")
    public Optional<Review> updateReview(@PathVariable UUID id, @RequestBody ReviewDto review){
        return reviewService.updateReview(id, review);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable UUID id){
        reviewService.deleteReview(id);
    }
}
