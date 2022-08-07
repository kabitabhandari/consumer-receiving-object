package com.example.consumerreceivingobject;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerClass {


    @KafkaListener(topics = "blacktea", groupId = "blacktea_id")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }


    @KafkaListener(topics = "${spring.kafka.consumer.consumer-one.topic}",
            containerFactory = "userKafkaListenerFactory")
    public void consumeJson(ConsumerRecord consumerRecord) {
        log.info("Received message with key={}, partition={}, offset={} and value={} on topic={}",
                consumerRecord.key(),
                consumerRecord.partition(),
                consumerRecord.offset(),
                consumerRecord.value(),
                consumerRecord.topic());

        if(isValidRecord(consumerRecord)){
        System.out.println("[Consumed Message]:: " + consumerRecord.value());}

    }

    private boolean isValidRecord(ConsumerRecord consumerRecord) {
        if(consumerRecord != null && consumerRecord.value() !=null ){
            return true;
        }
        return false;
    }

}
