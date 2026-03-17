package com.avaneesh.notifcation_worker.strategy;

import com.avaneesh.notifcation_worker.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("EMAIL")
public class EmailNotificationStrategy implements NotificationStrategy {

    @Autowired
    private EmailService emailService;

    @Override
    public void send(String recipient, String subject, String body) {
        emailService.sendEmail(recipient, subject, body);
    }
}