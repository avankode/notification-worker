package com.avaneesh.notifcation_worker.service;

import com.avaneesh.notifcation_worker.dto.NotificationRequestDTO;
import com.avaneesh.notifcation_worker.entity.NotificationLog;
import com.avaneesh.notifcation_worker.repository.NotificationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationLogService {

    @Autowired
    private NotificationLogRepository notificationLogRepository;

    public NotificationLog createProcessingLog(NotificationRequestDTO request) {
        NotificationLog log = NotificationLog.builder()
                .recipientEmail(request.getRecipientEmail())
                .subject(request.getSubject())
                .messageBody(request.getMessageBody())
                .status("PROCESSING")
                .build();

        return notificationLogRepository.save(log);
    }

    public void markAsSent(NotificationLog log) {
        log.setStatus("SENT");
        notificationLogRepository.save(log);
    }

    public void markAsFailed(NotificationLog log) {
        log.setStatus("FAILED");
        notificationLogRepository.save(log);
    }
}