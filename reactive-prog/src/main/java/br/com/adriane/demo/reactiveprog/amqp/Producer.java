package br.com.adriane.demo.reactiveprog.amqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    private final String exchange = "topic.contact";

    private final String routingKey = "routingKey.contact";

    private final AmqpTemplate amqpTemplate;

    public Producer(final AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void send(ContactMessage msg) {
        amqpTemplate.convertAndSend(exchange, routingKey, msg);
    }
}
