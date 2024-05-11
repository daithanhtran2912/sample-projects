package com.example.kafka.springbootkafkaproducer.thread;

import com.example.kafka.springbootkafkaproducer.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProductThread implements Runnable {

    Logger logger = LoggerFactory.getLogger(ProductThread.class);

    private Thread worker;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicBoolean stopped = new AtomicBoolean(true);
    private static final int INTERVAL = 60000; // 1 minute

    private final ProductService productService;

    public ProductThread(ProductService productService) {
        this.productService = productService;
    }

    public void start() {
        worker = new Thread(this);
        logger.info("{} - ProductThread starting...", LocalDateTime.now());
        worker.start();
    }

    public void stop() {
        running.set(false);
        logger.info("{} - ProductThread stopped!", LocalDateTime.now());
    }

    public void interrupt() {
        running.set(false);
        worker.interrupt();
    }

    boolean isRunning() {
        return running.get();
    }

    boolean isStopped() {
        return stopped.get();
    }

    @Override
    public void run() {
        running.set(true);
        stopped.set(false);
        while (running.get()) {
            try {
                productService.publishProductData();
                Thread.sleep(INTERVAL);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Thread was interrupted, Failed to complete operation. {} {}", e.getMessage(), e);
            }
        }
        stopped.set(true);
    }
}
