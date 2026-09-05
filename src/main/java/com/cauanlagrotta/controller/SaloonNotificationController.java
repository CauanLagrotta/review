package com.cauanlagrotta.controller;

import com.cauanlagrotta.mapper.NotificationMapper;
import com.cauanlagrotta.model.Notification;
import com.cauanlagrotta.payload.dto.BookingDTO;
import com.cauanlagrotta.payload.dto.NotificationDTO;
import com.cauanlagrotta.service.NotificationService;
import com.cauanlagrotta.service.client.BookingFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications/saloon-owner")
public class SaloonNotificationController {
    
    private final NotificationService notificationService;
    private final BookingFeignClient bookingFeignClient;
    
    @GetMapping("/saloon/{saloonId}")
    public ResponseEntity<List<NotificationDTO>> getNotificationBySaloonId(@PathVariable Long saloonId){
        
        List<Notification> notifications = notificationService.getAllNotificationBySaloonId(saloonId);
        
        List<NotificationDTO> notificationDTOS = notifications.stream().map((notification -> {
            BookingDTO bookingDTO = bookingFeignClient.getById(notification.getBookingId()).getBody();
            
            if (isNull(bookingDTO)){
                throw new RuntimeException("bookingDTO cannot be null");
            }
            
            return NotificationMapper.toDTO(notification, bookingDTO);
        })).toList();
        
        return ResponseEntity.ok(notificationDTOS);
    }
}
