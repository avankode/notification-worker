package com.avaneesh.notifcation_worker.listener;

import com.avaneesh.notifcation_worker.client.WebhookHttpClient;
import com.avaneesh.notifcation_worker.dto.WebhookPayload;
import com.avaneesh.notifcation_worker.event.NotificationProcessedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebhookDispatcherService implements NotificationListener{

    private final WebhookHttpClient webhookHttpClient;
    private final ObjectMapper objectMapper;

    @Override
    public void onNotificationProcessed(NotificationProcessedEvent event) {
        if (event.getWebhookUrl() == null || event.getWebhookUrl().isBlank()) {
            return;
        }

        try {
            WebhookPayload payload = new WebhookPayload(
                    event.getTenantId(),
                    event.getStatus(),
                    event.getChannel()
            );

            String json = objectMapper.writeValueAsString(payload);

            webhookHttpClient.send(event.getWebhookUrl(), json);

            log.info(" Webhook dispatched for tenant {}", event.getTenantId());

        } catch (Exception e) {
            log.error(" Failed webhook dispatch", e);
        }
    }
}
