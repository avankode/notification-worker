package com.avaneesh.notifcation_worker.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    // Make sure "notification_queue" matches the exact name of the queue your API sends to!
    @RabbitListener(queues = "notification_queue")
    public void processNotification(String message) {

        System.out.println("🚨 NEW MESSAGE RECEIVED 🚨");
        System.out.println("Payload: " + message);

        // Later, we will add the actual logic here to send an Email or SMS
        // and save the status to your PostgreSQL database!
    }
}