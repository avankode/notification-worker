package com.avaneesh.notifcation_worker.strategy;

public interface NotificationStrategy {
    void send(String recipient, String subject, String messageBody);
}