package com.thanhtd.springboot.service;

import com.thanhtd.springboot.dto.MessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {

    private final TCPClientGateway tcpClientGateway;

    public MessageDTO send(String message) throws IOException {
        var messageRequest = MessageDTO.builder()
                .message(message)
                .sender("tcp-client")
                .timestamp(LocalDateTime.now().toString())
                .build();

        Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();
        String messageRequestStr = mapper.toJson(messageRequest);

        log.info("Sending message: {}", messageRequestStr);
        byte[] responseByte = tcpClientGateway.send(messageRequestStr.getBytes());
        var response = mapper.fromJson(new String(responseByte), MessageDTO.class);
        log.info("Receive message: {}, from: {}, at: {}",
                response.getMessage(),
                response.getSender(),
                response.getTimestamp());
        return response;
    }
}
