package com.demo.consumers;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class SimpleConsumer {
	   public static void main(String[] args) throws Exception{

           String topicName = "welcometopic";
           String groupName = "welcomegroup";

           Properties props = new Properties();
           props.put("bootstrap.servers", "localhost:9092");
           props.put("group.id", groupName);
           props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
           props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
           

           KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
           System.out.println("Created Kafkaconsumer");
           consumer.subscribe(Arrays.asList(topicName));
           System.out.println("KafkaConsumer Subscribed to topics");
           while (true){
                   ConsumerRecords<String, String> records = consumer.poll(100);
                   for (ConsumerRecord<String, String> record : records){
                           System.out.println("Received message:" + record.toString());
                   }
           }
           
           

   }
}
