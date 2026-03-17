package com.avaneesh.notifcation_worker.service;

import com.avaneesh.notifcation_worker.dto.NotificationRequestDTO;
import com.avaneesh.notifcation_worker.entity.NotificationLog;
import com.avaneesh.notifcation_worker.enums.NotificationStatus;
import com.avaneesh.notifcation_worker.repository.NotificationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationLogService {

    @Autowired
    private NotificationLogRepository notificationLogRepository;

    public NotificationLog createProcessingLog(NotificationRequestDTO request) {
        NotificationLog notificationLog = NotificationLog.builder()
                .recipientEmail(request.getRecipient())
                .subject(request.getSubject())
                .messageBody(request.getMessageBody())
                .status(NotificationStatus.PROCESSING.name())
                .build();

        return notificationLogRepository.save(notificationLog);
    }

    public void markAsSent(NotificationLog notificationLog) {
        notificationLog.setStatus(NotificationStatus.SENT.name());
        notificationLogRepository.save(notificationLog);
    }

    public void markAsFailed(NotificationLog notificationLog) {
        notificationLog.setStatus(NotificationStatus.FAILED.name());
        notificationLogRepository.save(notificationLog);
    }
}