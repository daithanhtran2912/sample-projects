package com.thanhtd.spring_boot_integration_tcp.service.impl;

import com.thanhtd.spring_boot_integration_tcp.config.TCPClientGateway;
import com.thanhtd.spring_boot_integration_tcp.dto.MessageDTO;
import com.thanhtd.spring_boot_integration_tcp.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final TCPClientGateway tcpClientGateway;

    public MessageDTO send(String message) throws IOException {
        MessageDTO requestDto = MessageDTO.builder()
                .message(message)
                .sender("server")
                .timestamp(LocalDateTime.now().toString())
                .build();
        Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();
        var requestJson = mapper.toJson(requestDto);
        log.info("<<< Sending request to client: {}", requestJson);
        var requestByte = requestJson.getBytes();
        var responseStr = tcpClientGateway.send(requestByte);
        log.info(">>> Received response from client: {}", responseStr);
        return mapper.fromJson(responseStr, MessageDTO.class);
    }
}
