package com.cauanlagrotta.service;

import com.cauanlagrotta.dto.ReviewRequest;
import com.cauanlagrotta.dto.SaloonDTO;
import com.cauanlagrotta.dto.UserDTO;
import com.cauanlagrotta.model.Review;

import java.util.List;

public interface ReviewService {
    
    Review createReview(ReviewRequest req, UserDTO user, SaloonDTO saloon);
    
    List<Review> getReviewsBySaloonId(Long saloonId);
    
    Review updateReview(ReviewRequest req, Long userId, Long reviewId);
    
    void deleteReview(Long reviewId, Long userId);
}