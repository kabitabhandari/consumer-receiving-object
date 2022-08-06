package com.example.consumerreceivingobject;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@SpringBootApplication
public class ConsumerReceivingObjectApplication {

	public static void main(String[] args) {

		String topic = "producer-contactdetails-topic";
		String groupName = "groupName-123";

		// producer properties, following 3 are mandatory props
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "com.example.consumerreceivingobject.MyDeserializer");
		props.put("consumer.security.protocol", "SASL_SSL");
		 props.put("group.id", groupName);
		props.put("enable.auto.commit", "false");



		KafkaConsumer<String, MessageSupplier> consumer = null;

		try {
			consumer = new KafkaConsumer<>(props);
			consumer.subscribe(Arrays.asList(topic));

			while (true){
				ConsumerRecords<String, MessageSupplier> records = consumer.poll(Duration.ofMillis(100));
				for (ConsumerRecord<String, MessageSupplier> record : records){
					System.out.println("Supplier id= " + record.value().getId() + " Supplier  Name = " + record.value().getName() + " Supplier Start Date = " + record.value().getDate().toString());
				}
				consumer.commitAsync();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			consumer.commitSync();
			//consumer.close();
		}
	}
}
