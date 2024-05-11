package com.example.kafka.springbootkafkaproducer.controller;

import com.example.kafka.springbootkafkaproducer.service.ProductService;
import com.example.kafka.springbootkafkaproducer.thread.ProductThread;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class ProductController {

    private final ProductThread productThread;

    public ProductController(ProductService productService) {
        this.productThread = new ProductThread(productService);
    }

    @GetMapping("/activate")
    public String activate() {
        productThread.start();
        return "Running....";
    }

    @GetMapping("/terminate")
    public String terminate() {
        productThread.stop();
        return "Stopped!";
    }
}
