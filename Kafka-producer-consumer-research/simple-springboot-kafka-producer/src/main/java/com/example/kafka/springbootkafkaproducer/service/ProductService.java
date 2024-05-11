package com.example.kafka.springbootkafkaproducer.service;

import com.example.kafka.springbootkafkaproducer.model.BaseResponse;
import com.example.kafka.springbootkafkaproducer.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class ProductService {

    Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProductService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private static final String PRODUCT_TOPIC = "kafka.topic.feed.products";
    private static final String PRODUCT_DATA_URL =
            "https://fakerapi.it/api/v1/products?_quantity=50&_locale=en_US&_taxes=10&_categories_type=string";

    public void publishProductData() {
        String productData = getProductData();
        kafkaTemplate.send(PRODUCT_TOPIC, productData);
        logger.info("{} - Published product to topic: {} - with body: {}", LocalDateTime.now(), PRODUCT_TOPIC, productData);
    }

    private String getProductData() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(PRODUCT_DATA_URL, String.class);
        return response.getBody();
    }
}
