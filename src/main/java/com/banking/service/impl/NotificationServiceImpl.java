package com.banking.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banking.entity.Notification;
import com.banking.entity.User;
import com.banking.model.NotificationStatus;
import com.banking.repository.NotificationRepository;
import com.banking.repository.UserRepository;
import com.banking.service.NotificationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

	
	
	private final NotificationRepository notificationRepo;
	private final UserRepository userRepo;
	
	public Notification createNotification(String userId, String message) {
	    User user = userRepo.findById(userId)
	        .orElseThrow(() -> new RuntimeException("User not found"));

	    Notification notification = new Notification();
	    notification.setUser(user);
	    notification.setMessage(message);

	    return notificationRepo.save(notification);
	}
	
	@Override
	public List<Notification> getNotificationsByUserId(String userId) {
		return notificationRepo.findByUser_UserId(userId);
	}

	@Override
	public List<Notification> getNotificationsByStatus(NotificationStatus status) {
		return notificationRepo.findByStatus(status);
	}

	

}
