package com.avaneesh.notifcation_worker.dto;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class NotificationRequestDTO implements Serializable {

    private String tenantId;
    private String channel;
    private String recipient;
    private String subject;
    private String messageBody;

    @Override
    public String toString() {
        return "To: " + recipient + " | Subject: " + subject;
    }
}
