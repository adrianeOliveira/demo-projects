package br.com.rabbitmq.rabbitmqproject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Sender {

	private final Logger log = LogManager.getLogger(Sender.class);

	@Value("${rabbitmq.exchange.name}")
	private String topicExchangeName;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void send () {
		log.info("Sending message...");
		rabbitTemplate.convertAndSend(topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");
		log.info("Message sent.");
	}
}
