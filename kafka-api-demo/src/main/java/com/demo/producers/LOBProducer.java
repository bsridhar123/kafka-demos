package com.demo.producers;

import java.util.*;
import org.apache.kafka.clients.producer.*;
public class LOBProducer {

   public static void main(String[] args) throws Exception{

      String topicName = "LOBTopic";

      Properties props = new Properties();
      props.put("bootstrap.servers", "localhost:9092,localhost:9093");
      props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
      props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
      props.put("partitioner.class", "com.demo.partitioner.LOBPartitioner");
      props.put("lob.name", "SENIOR");

      Producer<String, String> producer = new KafkaProducer <>(props);

         for (int i=0 ; i<10 ; i++)
         producer.send(new ProducerRecord<>(topicName,"ADMIN"+i,"500"+i));

         for (int i=0 ; i<10 ; i++)
         producer.send(new ProducerRecord<>(topicName,"GENERAL","500"+i));

         
         System.out.println("Message is sent to LOBProducer");
         producer.close();

          System.out.println("LOBProducer Completed.");
   }
}