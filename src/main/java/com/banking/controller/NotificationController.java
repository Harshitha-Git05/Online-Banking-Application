package com.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.entity.Notification;
import com.banking.model.NotificationStatus;
import com.banking.service.NotificationService;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;
	
	@GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(notificationService.getNotificationsByUserId(userId));
    }
	
	 @GetMapping("/status/{status}")
	    public ResponseEntity<List<Notification>> getByStatus(@PathVariable NotificationStatus status) {
	        return ResponseEntity.ok(notificationService.getNotificationsByStatus(status));
	    }
	

}
