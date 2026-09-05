package com.cauanlagrotta.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    private String reviewText;
    
    @Column(nullable = false)
    private double rating;
    
    @Column(nullable = false)
    private Long saloonId;
    
    @Column(nullable = false)
    private Long userId;
    
    @CreatedDate
    private LocalDateTime createdAt;
}
