package com.avaneesh.notifcation_worker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HealthCheckController {

    @GetMapping("/health")
    public String healthCheck() {
        log.info("Worker is awake, healthy, and ready to process messages!");
        return "Worker is awake, healthy, and ready to process messages!";
    }
}