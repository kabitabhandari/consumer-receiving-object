package com.example.consumerreceivingobject;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@ConfigurationProperties(prefix = "spring.kafka.consumer")
@Data
public class ConsumerConfigurationProperties {

    protected ConsumerTwo consumerTwo;
    protected ConsumerOne consumerOne;
    protected String keyDeserializer;
    protected String valueDeserializer;
    protected String bootstrapServers;

    @Data
    public static class ConsumerTwo{
        protected String groupId;
        protected String topic;

    }
    @Data
    public static class ConsumerOne{
        protected String groupId;
        protected String topic;

    }

}
