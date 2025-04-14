package com.banking.service;

import java.util.List;

import com.banking.entity.Notification;
import com.banking.model.NotificationStatus;

public interface NotificationService {
	
	Notification createNotification(String userId, String message);
	List<Notification> getNotificationsByUserId(String userId);
    List<Notification> getNotificationsByStatus(NotificationStatus status);

}
