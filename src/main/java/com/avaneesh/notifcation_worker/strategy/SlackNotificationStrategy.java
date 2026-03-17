package com.avaneesh.notifcation_worker.strategy;

import com.avaneesh.notifcation_worker.service.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("SLACK")
public class SlackNotificationStrategy implements NotificationStrategy {

    @Autowired
    private SlackService slackService;

    @Override
    public void send(String recipient, String subject, String body) {
        String message = "*Subject:* " + subject + "\n" + "*Message:* " + body;
        slackService.sendMessage(message);
    }
}