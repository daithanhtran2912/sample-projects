package com.thanhtd.spring_boot_integration_tcp.config;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "outboundChannel")
public interface TCPClientGateway {

    String send(byte[] message);
}
