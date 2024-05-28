package com.thanhtd.spring_boot_integration_tcp.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thanhtd.spring_boot_integration_tcp.dto.MessageDTO;
import com.thanhtd.spring_boot_integration_tcp.handler.MessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class MessageHandlerImpl implements MessageHandler {

    @Override
    public String handleMessage(byte[] message) {
        log.info(">>> Received request from client: {}", new String(message));
        Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();
        var response = MessageDTO.builder()
                .message("Ack from server")
                .sender("server")
                .timestamp(LocalDateTime.now().toString())
                .build();
        String responseJson = "";
        try {
            responseJson = mapper.toJson(response);
        } catch (JsonProcessingException e) {
            log.error("Unable to parse response: {}", e.getMessage());
        }
        log.info("<<< Sending response to client: {}", responseJson);
        return responseJson;
    }
}
