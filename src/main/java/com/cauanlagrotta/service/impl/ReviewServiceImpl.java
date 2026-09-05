package com.cauanlagrotta.service.impl;

import com.cauanlagrotta.dto.ReviewRequest;
import com.cauanlagrotta.dto.SaloonDTO;
import com.cauanlagrotta.dto.UserDTO;
import com.cauanlagrotta.model.Review;
import com.cauanlagrotta.repository.ReviewRepository;
import com.cauanlagrotta.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    
    @Override
    public Review createReview(ReviewRequest req, UserDTO user, SaloonDTO saloon) {
        Review review = new Review();
        review.setReviewText(req.getReviewText());
        review.setRating(req.getRating());
        review.setUserId(user.getId());
        review.setSaloonId(saloon.getId());
        
        return reviewRepository.save(review);
    }
    
    @Override
    public List<Review> getReviewsBySaloonId(Long saloonId) {
        return reviewRepository.findBySaloonId(saloonId);
    }
    
    private Review getReviewById(Long reviewId){
        return reviewRepository.findById(reviewId).orElseThrow(RuntimeException::new);
    }
    
    @Override
    public Review updateReview(ReviewRequest req, Long userId, Long reviewId) {
        Review review = getReviewById(reviewId);
        
        if(!review.getUserId().equals(userId)) throw new RuntimeException();
        
        review.setReviewText(req.getReviewText());
        review.setRating(req.getRating());
        
        return reviewRepository.save(review);
    }
    
    @Override
    public void deleteReview(Long reviewId, Long userId) {
        Review review = getReviewById(reviewId);
        
        if(!review.getUserId().equals(userId)) throw new RuntimeException();
        
        reviewRepository.delete(review);
    }
}
