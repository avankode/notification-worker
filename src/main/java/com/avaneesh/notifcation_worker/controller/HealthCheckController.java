package com.avaneesh.notifcation_worker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/health")
    public String healthCheck() {
        // Render will hit this URL to verify the app is alive
        return "Worker is awake, healthy, and ready to process messages!";
    }
}