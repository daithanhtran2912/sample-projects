package com.thanhtd.spring_boot_integration_tcp.dto;

import com.thanhtd.spring_boot_integration_tcp.service.MessageService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    @Override
    @SneakyThrows
    public String handleMessage(byte[] message) {
        log.info("Received message: {}", new String(message));
        Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();
        var response = MessageDTO.builder()
                .message("Ack from server")
                .sender("server")
                .timestamp(LocalDateTime.now().toString())
                .build();
        return mapper.toJson(response);
    }
}
