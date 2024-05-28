package com.thanhtd.spring_boot_integration_tcp.config;

import com.thanhtd.spring_boot_integration_tcp.handler.MessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.ip.dsl.Tcp;
import org.springframework.integration.ip.tcp.connection.AbstractClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.CachingClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNioClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNioServerConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.ByteArrayCrLfSerializer;
import org.springframework.lang.NonNull;
import org.springframework.messaging.MessageHeaders;

@Slf4j
@Configuration
@EnableIntegration
@RequiredArgsConstructor
public class TCPServerConfig implements ApplicationEventPublisherAware {

    private final MessageHandler messageHandler;

    @Value("${tcp.server.port}")
    private int serverPort;

    @Value("${tcp.client.host}")
    private String clientHost;

    @Value("${tcp.client.port}")
    private int clientPort;

    @Value("${tcp.client.poolSize}")
    private int poolSize;

    @Value("${tcp.client.timeout}")
    private int timeout;

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(@NonNull ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * <p>Received incoming message & response through handleIncomingMessage()</p>
     *
     * @see org.springframework.integration.ip.tcp.TcpInboundGateway
     * @see IntegrationFlow
     * @see MessageHandler
     */
    @Bean
    public IntegrationFlow tcpInboundChannel(ApplicationContext applicationContext) {
        var serverConnectionFactory = (AbstractServerConnectionFactory) applicationContext
                .getBean("serverConnectionFactory");
        return IntegrationFlow.from(Tcp.inboundGateway(serverConnectionFactory))
                .handle((this::handleIncomingMessage))
                .get();
    }

    /**
     * <p>Sending request message using outboundChannel</p>
     *
     * @see org.springframework.integration.ip.tcp.TcpOutboundGateway
     * @see IntegrationFlow
     * @see DirectChannel
     * @see MessageChannels
     */
    @Bean
    public IntegrationFlow tcpOutboundChannel(ApplicationContext applicationContext) {
        var clientConnectionFactory = (AbstractClientConnectionFactory) applicationContext
                .getBean("clientConnectionFactory");
        return IntegrationFlow.from("outboundChannel")
                .handle(Tcp.outboundGateway(clientConnectionFactory).remoteTimeout(timeout))
                .transform(Transformers.objectToString())
                .get();
    }

    @Bean
    public DirectChannel outboundChannel() {
        return MessageChannels.direct().getObject();
    }

    /**
     * <p>Create AbstractServerConnectionFactory using TcpNioServerConnectionFactory with server port</p>
     *
     * @return TcpNioServerConnectionFactory
     * @see AbstractServerConnectionFactory
     * @see TcpNioServerConnectionFactory
     */
    @Bean
    public AbstractServerConnectionFactory serverConnectionFactory() {
        var connectionFactory = new TcpNioServerConnectionFactory(serverPort);
        connectionFactory.setSingleUse(false);
        connectionFactory.setApplicationEventPublisher(applicationEventPublisher);
        connectionFactory.setSerializer(codec());
        connectionFactory.setDeserializer(codec());
        return connectionFactory;
    }

    /**
     * <p>Create AbstractClientConnectionFactory</p>
     * <p>Using TcpNioClientConnectionFactory to connect to client host and port</p>
     *
     * @return new CachingClientConnectionFactory
     * @see AbstractClientConnectionFactory
     * @see TcpNioClientConnectionFactory
     * @see CachingClientConnectionFactory
     */
    @Bean
    public AbstractClientConnectionFactory clientConnectionFactory() {
        var connectionFactory = new TcpNioClientConnectionFactory(clientHost, clientPort);
        connectionFactory.setUsingDirectBuffers(true);
        connectionFactory.setApplicationEventPublisher(applicationEventPublisher);
        connectionFactory.setSingleUse(false);
        connectionFactory.setSerializer(codec());
        connectionFactory.setDeserializer(codec());
        return new CachingClientConnectionFactory(connectionFactory, poolSize);
    }

    /**
     * <p>Codec for serialize & deserialize message</p>
     *
     * @return new ByteArrayCrLfSerializer
     * @see ByteArrayCrLfSerializer
     * @see TcpNioClientConnectionFactory
     */
    private ByteArrayCrLfSerializer codec() {
        var serializer = new ByteArrayCrLfSerializer();
        serializer.setMaxMessageSize(8096);
        return serializer;
    }

    private String handleIncomingMessage(byte[] payload, MessageHeaders headers) {
        return messageHandler.handleMessage(payload);
    }
}
