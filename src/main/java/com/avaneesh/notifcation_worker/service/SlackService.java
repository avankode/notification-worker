package com.avaneesh.notifcation_worker.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Slf4j
@Service
public class SlackService {

    @Value("${spring.slack.webhook-url}")
    private String webhookUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendMessage(String message) {
        try {
            log.info("⏳ Sending Slack message...");

            Map<String, String> payload = Map.of("text", message);

            restTemplate.postForObject(webhookUrl, payload, String.class);

            log.info("✅ Slack message sent!");
        } catch (Exception e) {
            log.error("❌ FAILED to send Slack message: {}", e.getMessage());
            throw new RuntimeException("Slack sending failed", e);
        }
    }
}