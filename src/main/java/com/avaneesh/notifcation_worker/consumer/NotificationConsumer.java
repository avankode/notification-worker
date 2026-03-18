package com.avaneesh.notifcation_worker.consumer;

import com.avaneesh.notifcation_worker.dto.NotificationRequestDTO;
import com.avaneesh.notifcation_worker.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class NotificationConsumer {

    @Autowired
    private NotificationService notificationService;

    @RabbitListener(queues = "notification_queue_json")
    public void receiveMessage(NotificationRequestDTO request) {

        log.info("======================================");
        log.info(" NEW JSON TASK RECEIVED ");
        log.info("Sending email to: {}" , request.getRecipient());
        log.info("Subject: {}" , request.getSubject());
        log.info("Body: {}" , request.getMessageBody());
        log.info("======================================");

        notificationService.processNotification(request);
        log.info("Successfully saved the message to DB {}", request.getRecipient());

    }
}