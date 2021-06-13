package br.com.adriane.demo.reactiveprog.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    private static final String DEFAULT_QUEUE = "queue.contact";

    private static final String DEFAULT_ROUTINGKEY = "routingKey.contact";

    @Bean
    public TopicExchange topic() {
        return new TopicExchange("topic.contact");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(DEFAULT_ROUTINGKEY);
    }

    @Bean
    Queue queue() {
        return new Queue(DEFAULT_QUEUE, true);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}
