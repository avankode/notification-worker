package com.avaneesh.notifcation_worker.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotificationProcessedEvent {
    private final String tenantId;
    private final String status;
    private final String channel;
    private final String webhookUrl;
}
