package com.avaneesh.notifcation_worker.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Slf4j
@Component
public class WebhookHttpClient {

    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    public void send(String url, String payload) {

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .build();

            httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenAccept(response ->
                            log.info(" Webhook fired! Status: {}", response.statusCode())
                    )
                    .exceptionally(e -> {
                        log.error(" Webhook failed: {}", e.getMessage());
                        return null;
                    });

        } catch (Exception e) {
            log.error(" Error building webhook request", e);
        }
    }
}