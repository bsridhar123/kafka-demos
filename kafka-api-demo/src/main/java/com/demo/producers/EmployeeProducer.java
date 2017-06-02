package com.demo.producers;

import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.kafka.clients.producer.*;

import com.demo.model.Employee;

public class EmployeeProducer {

	public static void main(String[] args) throws Exception {

		String topicName = "employeetopic";

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "com.demo.producers.EmployeeSerializer");

		Producer<String, Employee> producer = new KafkaProducer<>(props);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Employee sp1 = new Employee(103, "JoK", df.parse("2016-04-01"));
		
		
		System.out.println("Message being sent to Producer...");
		producer.send(new ProducerRecord<String, Employee>(topicName, "Employee1", sp1)).get();
		System.out.println("Message is sent to Producer");
		
		
		System.out.println("EmployeeProducer Completed.");
		producer.close();

	}
}