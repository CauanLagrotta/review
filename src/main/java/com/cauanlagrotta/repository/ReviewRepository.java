package com.cauanlagrotta.repository;

import com.cauanlagrotta.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findBySaloonId(Long saloonId);
}
