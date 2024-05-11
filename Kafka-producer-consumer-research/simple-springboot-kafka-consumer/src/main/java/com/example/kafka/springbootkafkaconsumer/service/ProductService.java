package com.example.kafka.springbootkafkaconsumer.service;

import com.example.kafka.springbootkafkaconsumer.model.BaseResponse;
import com.example.kafka.springbootkafkaconsumer.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    Logger logger = LoggerFactory.getLogger(ProductService.class);
    private static final String PRODUCT_TOPIC = "kafka.topic.feed.products";

    @KafkaListener(topics = PRODUCT_TOPIC, groupId = "products")
    public void listenToProductProducer(String products) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        BaseResponse response = objectMapper.readValue(products, BaseResponse.class);
        logger.info("Received products from producer: {}", response.toString());
    }
}
