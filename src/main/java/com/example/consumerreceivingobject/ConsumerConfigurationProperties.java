package com.example.consumerreceivingobject;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.kafka.consumer")
@Data
public class ConsumerConfigurationProperties {

    protected ConsumerGreentea consumerGreentea;
    protected ConsumerMilktea consumerMilktea;
    protected String keyDeserializer;
    protected String valueDeserializer;
    protected String bootstrapServers;

    @Data
    public static class ConsumerGreentea {
        protected String groupId;
        protected String topic;

    }
    @Data
    public static class ConsumerMilktea {
        protected String groupId;
        protected String topic;

    }

}
