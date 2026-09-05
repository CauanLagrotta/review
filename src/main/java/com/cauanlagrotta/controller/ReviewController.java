package com.cauanlagrotta.controller;

import com.cauanlagrotta.dto.ReviewRequest;
import com.cauanlagrotta.dto.SaloonDTO;
import com.cauanlagrotta.dto.UserDTO;
import com.cauanlagrotta.model.Review;
import com.cauanlagrotta.service.ReviewService;
import com.cauanlagrotta.service.client.SaloonFeignClient;
import com.cauanlagrotta.service.client.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final UserFeignClient userFeignClient;
    private final SaloonFeignClient saloonFeignClient;
    
    @PostMapping("/saloon/{saloonId}")
    public ResponseEntity<Review> createReview(@PathVariable Long saloonId,
                                               @RequestBody ReviewRequest req,
                                               @RequestHeader("Authorization") String jwt){
        
        UserDTO user = userFeignClient.getUserProfile(jwt).getBody();
        SaloonDTO saloon = saloonFeignClient.getById(saloonId).getBody();
        
        Review review = reviewService.createReview(req, user, saloon);
        
        return ResponseEntity.ok(review);
    }
    
    @GetMapping("/saloon/{saloonId}")
    public ResponseEntity<List<Review>> getReviewBySaloonId(@PathVariable Long saloonId,
                                                            @RequestHeader("Authorization") String jwt){
        
        SaloonDTO saloon = saloonFeignClient.getById(saloonId).getBody();
        
        List<Review> reviews = reviewService.getReviewsBySaloonId(saloon != null ? saloon.getId() : null);
        
        if(Objects.isNull(reviews)) throw new RuntimeException();
        
        return ResponseEntity.ok(reviews);
    }
    
    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long reviewId,
                                               @RequestBody ReviewRequest req,
                                               @RequestHeader("Authorization") String jwt){
        
        UserDTO user = userFeignClient.getUserProfile(jwt).getBody();
        
        Review review = reviewService.updateReview(req, reviewId, user != null ? user.getId() : null);
        
        if(Objects.isNull(review)) throw new RuntimeException();
        
        return ResponseEntity.ok(review);
    }
    
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId,
                                             @RequestHeader("Authorization") String jwt){
        
        UserDTO user = userFeignClient.getUserProfile(jwt).getBody();
        
        reviewService.deleteReview(reviewId, user != null ? user.getId() : null);
        
        return ResponseEntity.noContent().build();
    }
}
