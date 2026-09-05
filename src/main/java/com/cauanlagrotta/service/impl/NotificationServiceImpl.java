package com.cauanlagrotta.service.impl;

import com.cauanlagrotta.mapper.NotificationMapper;
import com.cauanlagrotta.model.Notification;
import com.cauanlagrotta.payload.dto.BookingDTO;
import com.cauanlagrotta.payload.dto.NotificationDTO;
import com.cauanlagrotta.repository.NotificationRepository;
import com.cauanlagrotta.service.NotificationService;
import com.cauanlagrotta.service.client.BookingFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    
    private final NotificationRepository notificationRepository;
    private final BookingFeignClient bookingFeignClient;
    
    @Override
    public NotificationDTO createNotification(Notification notification) {
        
        Notification savedNotification = notificationRepository.save(notification);
        BookingDTO bookingDTO = bookingFeignClient.getById(savedNotification.getBookingId()).getBody();
        
        if(Objects.isNull(bookingDTO)){
            throw new RuntimeException("bookingDTO cannot be null");
        }
        
        return NotificationMapper.toDTO(savedNotification, bookingDTO);
    }
    
    @Override
    public List<Notification> getAllNotificationByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }
    
    @Override
    public List<Notification> getAllNotificationBySaloonId(Long saloonId) {
        return notificationRepository.findBySaloonId(saloonId);
    }
    
    @Override
    public Notification markNotificationAsRead(Long notificationId) {
        return notificationRepository.findById(notificationId).map(notification -> {
            notification.setIsRead(true);
            return notificationRepository.save(notification);
        }).orElseThrow(RuntimeException::new);
    }
}
