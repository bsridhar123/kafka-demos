package com.demo.consumers;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.demo.model.Employee;

public class EmployeeConsumer {

	public static void main(String[] args) throws Exception {

		String topicName = "employeetopic";
		String groupName = "employeetopicgroup";

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", groupName);
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "com.demo.producers.EmployeeDeserializer");

		KafkaConsumer<String, Employee> consumer = null;

		try {
			consumer = new KafkaConsumer<>(props);
			consumer.subscribe(Arrays.asList(topicName));

			while (true) {
				ConsumerRecords<String, Employee> records = consumer.poll(100);
				for (ConsumerRecord<String, Employee> record : records) {
					System.out.println("Employee id= " + String.valueOf(record.value().getEmpId()) + " Employee  Name = "
							+ record.value().getEmpName() + " Employee Start Date = "
							+ record.value().getEmpStartDate().toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			consumer.close();
		}

	}
}