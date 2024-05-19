package com.thanhtd.spring_boot_integration_tcp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MessageService {

    private final TCPClientGateway tcpClientGateway;
    private static int counter = 0;

    @Scheduled(fixedDelay = 30000, initialDelay = 5000)
    public void sendMessage() {
        if (counter < 10) {
            var response = tcpClientGateway.send("Test message: " + LocalDateTime.now());
            log.info("### Server response: {}", response);
            increase();
        }
    }

    private void increase() {
        MessageService.counter++;
    }
}
