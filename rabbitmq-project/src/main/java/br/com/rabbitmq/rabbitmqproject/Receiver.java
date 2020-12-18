package br.com.rabbitmq.rabbitmqproject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;


@Component
public class Receiver {

	private final Logger log = LogManager.getLogger(Receiver.class);

	public void receive(String message) {
		log.info("Received message<{}>", message);
	}
}
