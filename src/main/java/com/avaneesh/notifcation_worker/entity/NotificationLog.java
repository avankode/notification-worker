package com.avaneesh.notifcation_worker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notification_logs")
public class NotificationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipientEmail;

    @Column(name = "tenant_id")
    private String tenantId;

    @Column(name = "webhook_url")
    private String webhookUrl;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String messageBody;

    private String status; // e.g., "PROCESSING", "SENT", "FAILED"

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

}