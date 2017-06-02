package com.demo.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SenderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SenderService.class);
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${topic.boot}")
	private String topic;

	@GetMapping("/api/send/{message}")
	public void sendMessage(@PathVariable String message) throws Exception {
		LOGGER.info("sending data='{}' to topic='{}'", message, topic);
		kafkaTemplate.send(topic, message);
	}
}
