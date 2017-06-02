package com.demo.producers;

import java.util.*;
import org.apache.kafka.clients.producer.*;
public class SimpleProducer {
  
   public static void main(String[] args) throws Exception{
           
      String topicName = "welcometopic";
	  String key = "welcomekey";
	  String value = "welcomevalue";
      
      Properties props = new Properties();
      props.put("bootstrap.servers", "localhost:9092");
      props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");         
      props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	        
      Producer<String, String> producer = new KafkaProducer <>(props);
	
	  ProducerRecord<String, String> record = new ProducerRecord<>(topicName,key,value);
	  producer.send(record);	       
	  System.out.println("Message is sent to Producer");
      producer.close();
	  
	  System.out.println("SimpleProducer Completed.");
   }
}
