package com.example.consumerreceivingobject;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerClass {


    @KafkaListener(topics = "blacktea", groupId = "blacktea_id")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }


    @KafkaListener(topics = "${spring.kafka.consumer.consumer-one.topic}", groupId = "${spring.kafka.consumer.consumer-one.group-id}",
            containerFactory = "userKafkaListenerFactory")
    public void consumeJson(User user) {
        System.out.println("Consumed JSON Message: " + user);
    }

}
