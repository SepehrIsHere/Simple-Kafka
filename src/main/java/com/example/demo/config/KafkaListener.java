package com.example.demo.config;

import org.springframework.stereotype.Component;

@Component
public class KafkaListener {

    @org.springframework.kafka.annotation.KafkaListener(
            topics = {"userTopic","productTopic"},groupId = "groupId"
    )
    void listen(String message){
        System.out.println(message);
    }
}
