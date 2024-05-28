package com.thanhtd.spring_boot_integration_tcp.handler;

public interface MessageHandler {
    String handleMessage(byte[] message);
}
