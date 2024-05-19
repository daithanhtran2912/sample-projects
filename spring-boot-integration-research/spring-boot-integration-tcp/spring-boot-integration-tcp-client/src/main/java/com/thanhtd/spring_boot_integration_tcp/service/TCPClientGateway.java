package com.thanhtd.spring_boot_integration_tcp.service;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "tcpClientChannel", errorChannel = "tcpClientErrorChannel")
public interface TCPClientGateway {

    String send(String message);
}
