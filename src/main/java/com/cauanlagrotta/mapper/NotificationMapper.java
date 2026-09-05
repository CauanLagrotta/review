package com.cauanlagrotta.mapper;

import com.cauanlagrotta.model.Notification;
import com.cauanlagrotta.payload.dto.BookingDTO;
import com.cauanlagrotta.payload.dto.NotificationDTO;

public class NotificationMapper {

    public static NotificationDTO toDTO(Notification notification, BookingDTO bookingDTO){
    
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setId(notification.getId());
        notificationDTO.setType(notification.getType());
        notificationDTO.setIsRead(notification.getIsRead());
        notificationDTO.setDescription(notification.getDescription());
        notificationDTO.setBookingId(bookingDTO.getId());
        notificationDTO.setUserId(notification.getUserId());
        notificationDTO.setSaloonId(notification.getSaloonId());
        notificationDTO.setCreatedAt(notification.getCreatedAt());
        return notificationDTO;
    }
}