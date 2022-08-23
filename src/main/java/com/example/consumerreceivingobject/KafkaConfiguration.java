package com.example.consumerreceivingobject;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;

/*
Springboot provides an auto configuration for Kafka via KafkaAutoConfiguration class.
When you use @EnableAutoConfiguration or @SpringBootApplication, Spring boot automatically configures Kafka for you.
If you don't use Springboot, then you would have to use @EnableKafka to configure Kafka for your Spring app.
 */



@EnableKafka
@Configuration
public class KafkaConfiguration {

    //e.g: https://stackoverflow.com/questions/48959141/is-there-a-code-sample-for-multiple-producers-in-spring-kafka

    protected  ConsumerConfigurationProperties consumerConfigurationProperties;

    @Autowired
    public KafkaConfiguration(ConsumerConfigurationProperties consumerConfigurationProperties) {

        this.consumerConfigurationProperties = consumerConfigurationProperties;
    }


    @Bean
    public ConsumerFactory<String, User> userConsumerFactory() {
        Map<String, Object> consumerProperties = new HashMap<>();

        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerConfigurationProperties.getBootstrapServers());
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, consumerConfigurationProperties.consumerMilktea.getGroupId());

        consumerProperties.put(VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        consumerProperties.put(KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);

        consumerProperties.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, consumerConfigurationProperties.getKeyDeserializer());
        consumerProperties.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, consumerConfigurationProperties.getValueDeserializer());

        consumerProperties.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, "false");

        return new DefaultKafkaConsumerFactory<>(consumerProperties, new StringDeserializer(),
                new JsonDeserializer<>(User.class));
    }

    @Bean("beanMilk")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, User>> userKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Employee> employeeConsumerFactory() {
        Map<String, Object> consumerProperties = new HashMap<>();

        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerConfigurationProperties.getBootstrapServers());
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, consumerConfigurationProperties.consumerGreentea.getGroupId());

        consumerProperties.put(KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        consumerProperties.put(VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);

        consumerProperties.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, consumerConfigurationProperties.getKeyDeserializer());
        consumerProperties.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, consumerConfigurationProperties.getValueDeserializer());

        consumerProperties.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, "false");

        return new DefaultKafkaConsumerFactory<>(consumerProperties, new StringDeserializer(),
                new JsonDeserializer<>(Employee.class));
    }

    @Bean("beanGreen")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Employee>> employeeKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Employee> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(employeeConsumerFactory());
        return factory;
    }

}
