package com.cauanlagrotta.service;

import com.cauanlagrotta.model.Notification;
import com.cauanlagrotta.payload.dto.NotificationDTO;

import java.util.List;

public interface NotificationService {

    NotificationDTO createNotification(Notification notification);
    
    List<Notification> getAllNotificationByUserId(Long userId);
    
    List<Notification> getAllNotificationBySaloonId(Long saloonId);
    
    Notification markNotificationAsRead(Long notificationId);
}
