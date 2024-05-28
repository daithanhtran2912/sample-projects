package com.thanhtd.spring_boot_integration_tcp.service;

import com.thanhtd.spring_boot_integration_tcp.dto.MessageDTO;

import java.io.IOException;

public interface MessageService {
    MessageDTO send(String message) throws IOException;
}