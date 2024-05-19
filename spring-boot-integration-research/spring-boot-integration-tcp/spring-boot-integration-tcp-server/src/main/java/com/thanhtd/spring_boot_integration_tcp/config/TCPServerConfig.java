package com.thanhtd.spring_boot_integration_tcp.config;

import com.thanhtd.spring_boot_integration_tcp.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.ip.dsl.Tcp;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNioServerConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.ByteArrayCrLfSerializer;
import org.springframework.messaging.MessageHeaders;

@Slf4j
@Configuration
@EnableIntegration
@RequiredArgsConstructor
public class TCPServerConfig {

    private final MessageService messageService;

    @Value("${tcp.server.port}")
    private int port;

    @Bean
    public IntegrationFlow commandServerFlow() {
        return IntegrationFlow.from(Tcp.inboundGateway(serverConnectionFactory()))
                .handle((this::handleIncomingMessage))
                .get();
    }

    public AbstractServerConnectionFactory serverConnectionFactory() {
        var connectionFactory = new TcpNioServerConnectionFactory(port);
        connectionFactory.setSingleUse(false);
        connectionFactory.setSerializer(codec());
        connectionFactory.setDeserializer(codec());
        return connectionFactory;
    }

    private ByteArrayCrLfSerializer codec() {
        var serializer = new ByteArrayCrLfSerializer();
        serializer.setMaxMessageSize(8096);
        return serializer;
    }

    private String handleIncomingMessage(byte[] payload, MessageHeaders headers) {
        return messageService.handleMessage(payload);
    }
}
