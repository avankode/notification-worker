package com.avaneesh.notifcation_worker.service;

import com.avaneesh.notifcation_worker.dto.NotificationRequestDTO;
import com.avaneesh.notifcation_worker.entity.NotificationLog;
import com.avaneesh.notifcation_worker.strategy.NotificationStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class NotificationService {

    @Autowired
    private NotificationLogService logService;
    @Autowired
    private Map<String, NotificationStrategy> strategyMap;


    public void processNotification(NotificationRequestDTO request) {

        NotificationLog log = logService.createProcessingLog(request);

        try {
            NotificationStrategy strategy = getStrategy(request.getChannel());

            strategy.send(
                    request.getRecipient(),
                    request.getSubject(),
                    request.getMessageBody()
            );

            logService.markAsSent(log);

        } catch (Exception e) {
            logService.markAsFailed(log);
            throw e;
        }
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