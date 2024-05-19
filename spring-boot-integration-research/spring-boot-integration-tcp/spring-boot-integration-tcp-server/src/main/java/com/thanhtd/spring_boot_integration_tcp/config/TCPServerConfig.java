package com.thanhtd.spring_boot_integration_tcp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Configuration
@EnableIntegration
public class TCPServerConfig {

    private static final Logger log = LoggerFactory.getLogger(TCPServerConfig.class);
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
        log.info("Received message: {}", new String(payload));
        log.info("Received message headers: {}", headers);
        return "Thread: " + Thread.currentThread().getName() + " Server response...";
    }
}
