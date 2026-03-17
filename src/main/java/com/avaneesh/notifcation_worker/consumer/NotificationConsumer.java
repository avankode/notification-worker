package com.avaneesh.notifcation_worker.consumer;

import com.avaneesh.notifcation_worker.dto.NotificationRequestDTO;
import com.avaneesh.notifcation_worker.service.NotificationConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class NotificationConsumer {

    @Autowired
    private NotificationConsumerService notificationConsumerService;

    @RabbitListener(queues = "notification_queue_json")
    public void receiveMessage(NotificationRequestDTO request) {

        System.out.println("======================================");
        System.out.println("🚨 NEW JSON TASK RECEIVED 🚨");
        System.out.println("Sending email to: " + request.getRecipientEmail());
        System.out.println("Subject: " + request.getSubject());
        System.out.println("Body: " + request.getMessageBody());
        System.out.println("======================================");

        notificationConsumerService.saveNotificationLog(request);
        log.info("Successfully saved the message to DB "+ request.getRecipientEmail());

    }
}