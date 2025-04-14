package com.banking.dto;

import java.util.Date;
import java.util.UUID;

import com.banking.entity.User;
import com.banking.model.NotificationStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {
	
    private UUID notificationId;
    private User user;
    private String message;
    private NotificationStatus status;
    private Date createdAt;


}
