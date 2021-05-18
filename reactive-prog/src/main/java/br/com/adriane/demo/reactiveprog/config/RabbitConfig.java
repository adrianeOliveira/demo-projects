package br.com.adriane.demo.reactiveprog.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.adriane.demo.reactiveprog.consumers.Receiver;

@Configuration
public class RabbitConfig {
    private static final String DEFAULT_QUEUE = "queue.contact";

    @Bean
    public TopicExchange topic() {
        return new TopicExchange("topic.contact");
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("routingKey.contact");
    }

    @Bean
    Queue queue() {
        return new Queue(DEFAULT_QUEUE, true);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
        MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(DEFAULT_QUEUE);
        container.setMessageListener(listenerAdapter);
        return container;
    }
}
