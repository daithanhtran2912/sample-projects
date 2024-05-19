package com.thanhtd.springboot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class Scheduler {

    private final MessageService messageService;

    @Scheduled(fixedDelay = 30000, initialDelay = 5000)
    public void send() {
        try {
            var res = messageService.send("Hello from the other side!");
            log.info("res: {}", res.getMessage());
        } catch (IOException e) {
            log.error("Error at: {}, message: {}", e.getClass().getName(), e.getMessage());
        }
    }
}
