package com.avaneesh.notifcation_worker.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    @RabbitListener(queues = "notification_queue")
    public void receiveMessage(String message) {

        System.out.println("======================================");
        System.out.println("🚨 NEW BACKGROUND TASK RECEIVED 🚨");
        System.out.println("Processing Payload: " + message);
        System.out.println("======================================");

        // TODO: Next, we will write the logic to save this to PostgreSQL!
    }
}