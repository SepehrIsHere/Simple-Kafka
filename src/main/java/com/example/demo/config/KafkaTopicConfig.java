package com.example.demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    NewTopic userTopic() {
        return TopicBuilder.name("userTopic")
                .partitions(2)
                .replicas(1)
                .build();
    }

    @Bean
    NewTopic productTopic(){
        return TopicBuilder.name("productTopic")
                .partitions(2)
                .replicas(1)
                .build();
    }
}

