package com.thanhtd.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.tcp.TcpOutboundGateway;
import org.springframework.integration.ip.tcp.connection.AbstractClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.CachingClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNioClientConnectionFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class TCPClientConfiguration {

    @Value("${tcp.server.host}")
    private String host;

    @Value("${tcp.server.port}")
    private int port;

    @Value("${tcp.client.connection.poolSize}")
    private int connectionPoolSize;

    public static final String TCP_DEFAULT_CHANNEL = "tcp-channel-sample";

    @Bean
    public AbstractClientConnectionFactory clientConnectionFactory() {
        var connectionFactory = new TcpNioClientConnectionFactory(host, port);
        connectionFactory.setUsingDirectBuffers(true);
        return new CachingClientConnectionFactory(connectionFactory, connectionPoolSize);
    }

    @Bean(name = TCP_DEFAULT_CHANNEL)
    public MessageChannel messageChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = TCP_DEFAULT_CHANNEL)
    public MessageHandler messageHandler(AbstractClientConnectionFactory clientConnectionFactory) {
        var outboundGateway = new TcpOutboundGateway();
        outboundGateway.setConnectionFactory(clientConnectionFactory);
        return outboundGateway;
    }
}
