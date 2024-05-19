package com.thanhtd.spring_boot_integration_tcp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.ip.dsl.Tcp;
import org.springframework.integration.ip.tcp.connection.AbstractClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNioClientConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.ByteArrayCrLfSerializer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

@Slf4j
@Configuration
@EnableIntegration
public class TCPClientConfig {

    @Value("${tcp.server.host}")
    private String host;

    @Value("${tcp.server.port}")
    private int port;

    @Value("${tcp.client.timeout}")
    private int timeout;

    @Value("${tcp.client.poolSize}")
    private int poolSize;

    @Bean
    public DirectChannel tcpClientChannel() {
        return MessageChannels.direct().getObject();
    }

    @Bean
    public IntegrationFlow tcpClientToServerFlow() {
        return IntegrationFlow.from("tcpClientChannel")
                .handle(Tcp.outboundGateway(connectionFactory()).remoteTimeout(timeout))
                .transform(Transformers.objectToString())
                .get();

    }

    @Bean
    public DirectChannel tcpClientErrorChannel() {
        return MessageChannels.direct().getObject();
    }

    @Bean
    public IntegrationFlow tcpClientErrorChannelFlow() {
        return IntegrationFlow.from("tcpClientErrorChannel")
                .handle(new MessageHandler() {
                    @Override
                    public void handleMessage(Message<?> message) throws MessagingException {
                        log.error("Error communicating with tcp server. Message sent: {}", message.getPayload());
                    }
                })
                .get();
    }

    public AbstractClientConnectionFactory connectionFactory() {
        var connectionFactory = new TcpNioClientConnectionFactory(host, port);
        connectionFactory.setUsingDirectBuffers(true);
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
}
