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

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String messageBody;

    private String status; // e.g., "PROCESSING", "SENT", "FAILED"

    private LocalDateTime createdAt;

    // This automatically sets the timestamp right before Hibernate saves it!
    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

}