package com.avaneesh.notifcation_worker.dto;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class WebhookPayload {
    private String tenantId;
    private String status;
    private String channel;
}