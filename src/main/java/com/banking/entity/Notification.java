package com.banking.entity;

import java.util.Date;
import java.util.UUID;

import com.banking.model.NotificationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notification")
public class Notification {

	    @Id
	    private UUID notificationId;

	    @ManyToOne
	    @JoinColumn(name = "user_id", nullable = false)
	    private User user;

	    @Column(name = "message", nullable = false, length = 255)
	    private String message;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "status", nullable = false)
	    private NotificationStatus status;

	    @Column(name = "created_at", nullable = false, updatable = false)
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date createdAt;

	    // Auto-generate UUID and timestamp
	    @PrePersist
	    public void prePersist() {
	        this.notificationId = UUID.randomUUID();
	        this.createdAt = new Date();
	        this.status = NotificationStatus.SENT; // Default status
	    }

	}

