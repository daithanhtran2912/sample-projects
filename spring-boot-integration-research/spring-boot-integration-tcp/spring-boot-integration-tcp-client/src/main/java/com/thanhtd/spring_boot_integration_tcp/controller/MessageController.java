package com.thanhtd.spring_boot_integration_tcp.controller;

import com.thanhtd.spring_boot_integration_tcp.dto.MessageDTO;
import com.thanhtd.spring_boot_integration_tcp.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping(value = "/send")
    public MessageDTO send(@RequestParam("message") String message) throws IOException {
        return messageService.send(message);
    }
}
