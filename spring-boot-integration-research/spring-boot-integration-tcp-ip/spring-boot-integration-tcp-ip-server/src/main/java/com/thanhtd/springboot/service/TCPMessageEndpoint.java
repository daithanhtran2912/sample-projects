package com.thanhtd.springboot.service;

import com.thanhtd.springboot.config.TCPServerConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import java.io.IOException;

@Slf4j
@MessageEndpoint
@RequiredArgsConstructor
public class TCPMessageEndpoint {

    private final MessageService messageService;

    @ServiceActivator(inputChannel = TCPServerConfiguration.TCP_DEFAULT_CHANNEL)
    public byte[] process(byte[] message) throws IOException {
        String response = messageService.process(message);
        log.info("Send message to client");
        return response.getBytes();
    }
}
