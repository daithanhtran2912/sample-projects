package com.thanhtd.spring_boot_integration_tcp.handler.impl;

import com.thanhtd.spring_boot_integration_tcp.dto.MessageDTO;
import com.thanhtd.spring_boot_integration_tcp.handler.MessageHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class MessageHandlerImpl implements MessageHandler {

    @Override
    @SneakyThrows
    public String handleMessage(byte[] message) {
        log.info(">>> Received request from server: {}", new String(message));
        Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();
        var response = MessageDTO.builder()
                .message("Ack from client")
                .sender("client")
                .timestamp(LocalDateTime.now().toString())
                .build();
        var responseJson = mapper.toJson(response);
        log.info("<<< Sending response to server: {}", responseJson);
        return responseJson;
    }
}
