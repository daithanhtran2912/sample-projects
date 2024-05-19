package com.thanhtd.springboot.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.tcp.TcpInboundGateway;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNioServerConnectionFactory;
import org.springframework.messaging.MessageChannel;

@Configuration
public class TCPServerConfiguration {

    @Value("${tcp.server.port}")
    private int port;

    public static final String TCP_DEFAULT_CHANNEL = "tcp-channel-sample";

    @Bean
    public AbstractServerConnectionFactory serverConnectionFactory() {
        var connectionFactory = new TcpNioServerConnectionFactory(port);
        connectionFactory.setUsingDirectBuffers(true);
        return connectionFactory;
    }

    @Bean(name = TCP_DEFAULT_CHANNEL)
    public MessageChannel messageChannel() {
        return new DirectChannel();
    }

    @Bean
    public TcpInboundGateway tcpInboundGateway(AbstractServerConnectionFactory serverConnectionFactory,
                                               @Qualifier(value = TCP_DEFAULT_CHANNEL) MessageChannel messageChannel) {
        var gateway = new TcpInboundGateway();
        gateway.setConnectionFactory(serverConnectionFactory);
        gateway.setRequestChannel(messageChannel);
        return gateway;
    }
}
