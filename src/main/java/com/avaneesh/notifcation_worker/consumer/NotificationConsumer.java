package com.avaneesh.notifcation_worker.consumer;

import com.avaneesh.notifcation_worker.dto.NotificationRequestDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    @RabbitListener(queues = "notification_queue_json")
    public void receiveMessage(NotificationRequestDTO request) {

        System.out.println("======================================");
        System.out.println("🚨 NEW JSON TASK RECEIVED 🚨");
        System.out.println("Sending email to: " + request.getRecipientEmail());
        System.out.println("Subject: " + request.getSubject());
        System.out.println("Body: " + request.getMessageBody());
        System.out.println("======================================");

    }
}