package com.example.consumerreceivingobject;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.kafka.consumer")
@Data
public class ConsumerConfigurationProperties {
    protected String groupId;
    protected String keyDeserializer;
    protected String valueDeserializer;
    protected String bootstrapServers;
    protected String topic;
}
