package com.avaneesh.notifcation_worker.service;

import com.avaneesh.notifcation_worker.dto.NotificationRequestDTO;
import com.avaneesh.notifcation_worker.entity.NotificationLog;
import com.avaneesh.notifcation_worker.enums.NotificationStatus;
import com.avaneesh.notifcation_worker.repository.NotificationLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationLogService {

    @Autowired
    private NotificationLogRepository notificationLogRepository;

    public NotificationLog createProcessingLog(NotificationRequestDTO request) {
        NotificationLog notificationLog = NotificationLog.builder()
                .tenantId(request.getTenantId())
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
        log.info("logged message {} with {} status ", notificationLog.getId(), NotificationStatus.SENT);
    }

    public void markAsFailed(NotificationLog notificationLog) {
        notificationLog.setStatus(NotificationStatus.FAILED.name());
        notificationLogRepository.save(notificationLog);
        log.info("logged message {} with {} status ", notificationLog.getId(), NotificationStatus.FAILED);
    }
}