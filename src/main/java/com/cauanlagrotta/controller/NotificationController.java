package com.cauanlagrotta.controller;

import com.cauanlagrotta.mapper.NotificationMapper;
import com.cauanlagrotta.model.Notification;
import com.cauanlagrotta.payload.dto.BookingDTO;
import com.cauanlagrotta.payload.dto.NotificationDTO;
import com.cauanlagrotta.service.NotificationService;
import com.cauanlagrotta.service.client.BookingFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final BookingFeignClient bookingFeignClient;
    
    @PostMapping
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody Notification notification){
        return ResponseEntity.ok(notificationService.createNotification(notification));
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationDTO>> getNotificationByUserId(@PathVariable Long userId){
        List<Notification> notifications = notificationService.getAllNotificationByUserId(userId);
        List<NotificationDTO> notificationDTOS = notifications.stream().map((notification -> {
            BookingDTO bookingDTO = bookingFeignClient.getById(notification.getBookingId()).getBody();
            
            if (isNull(bookingDTO)){
                throw new RuntimeException("bookingDTO cannot be null");
            }
            
            return NotificationMapper.toDTO(notification, bookingDTO);
        })).toList();
        
        return ResponseEntity.ok(notificationDTOS);
    }
    
    @PutMapping("/{notificationId}/read")
    public ResponseEntity<NotificationDTO> markNotificationAsRead(@PathVariable Long notificationId){
        
        Notification notification = notificationService.markNotificationAsRead(notificationId);
        
        BookingDTO bookingDTO = bookingFeignClient.getById(notification.getBookingId()).getBody();
        
        if(isNull(bookingDTO)) throw new RuntimeException();
        
        return ResponseEntity.ok(NotificationMapper.toDTO(notification, bookingDTO));
    }
}
