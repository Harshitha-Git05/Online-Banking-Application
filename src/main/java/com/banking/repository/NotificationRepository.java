package com.banking.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.entity.Notification;
import com.banking.model.NotificationStatus;

public interface NotificationRepository extends JpaRepository<Notification, UUID>{

	List<Notification> findByUser_UserId(String userId);
	List<Notification> findByStatus(NotificationStatus status);
}
