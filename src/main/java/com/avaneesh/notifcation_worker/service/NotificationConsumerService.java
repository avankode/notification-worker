package com.avaneesh.notifcation_worker.service;

import com.avaneesh.notifcation_worker.dto.NotificationRequestDTO;
import com.avaneesh.notifcation_worker.entity.NotificationLog;
import com.avaneesh.notifcation_worker.repository.NotificationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumerService {

    @Autowired
    private NotificationLogRepository notificationLogRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private NotificationLogService notificationLogService;

    public void saveNotificationLog(NotificationRequestDTO request){

        NotificationLog log = notificationLogService.createProcessingLog(request);

        try {
            emailService.sendEmail(
                    request.getRecipientEmail(),
                    request.getSubject(),
                    request.getMessageBody()
            );

            notificationLogService.markAsSent(log);

        } catch (Exception e) {
            notificationLogService.markAsFailed(log);
        }
    }

    private NotificationLog notificationLogBuilder(NotificationRequestDTO request){
        return NotificationLog.builder()
                .recipientEmail(request.getRecipientEmail())
                .subject(request.getSubject())
                .messageBody(request.getMessageBody())
                .status("PROCESSING")
                .build();
    }
}
