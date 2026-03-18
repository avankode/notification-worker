package com.avaneesh.notifcation_worker.service;

import com.avaneesh.notifcation_worker.dto.NotificationRequestDTO;
import com.avaneesh.notifcation_worker.entity.NotificationLog;
import com.avaneesh.notifcation_worker.enums.NotificationStatus;
import com.avaneesh.notifcation_worker.event.NotificationProcessedEvent;
import com.avaneesh.notifcation_worker.strategy.NotificationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    @Autowired
    private NotificationLogService logService;
    @Autowired
    private Map<String, NotificationStrategy> strategyMap;
    @Autowired
    private final ApplicationEventPublisher eventPublisher;

    public void processNotification(NotificationRequestDTO request) {

        NotificationLog notificationLog = logService.createProcessingLog(request);
        String status;
        try {
            NotificationStrategy strategy = getStrategy(request.getChannel());

            strategy.send(
                    request.getRecipient(),
                    request.getSubject(),
                    request.getMessageBody()
            );

            logService.markAsSent(notificationLog);
            status = NotificationStatus.SENT.name();

        } catch (Exception e) {
            logService.markAsFailed(notificationLog);
            status = NotificationStatus.FAILED.name();
            log.error("Routing message : {} to DLQ ",notificationLog.getId());
            throw new AmqpRejectAndDontRequeueException("Routing message to Dead Letter Queue!", e);
        }

        eventPublisher.publishEvent(
                new NotificationProcessedEvent(
                        request.getTenantId(),
                        status,
                        "EMAIL",
                        request.getWebhookUrl()
                )
        );
    }

    private NotificationStrategy getStrategy(String channel) {
        NotificationStrategy strategy = strategyMap.get(channel);
        if (strategy == null) {
            log.warn("Unsupported channel");
            throw new IllegalArgumentException("Unsupported channel: " + channel);
        }
        return strategy;
    }
}