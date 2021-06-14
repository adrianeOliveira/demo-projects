package br.com.adriane.demo.reactiveprog.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@Configuration
public class RabbitConfig {
    private static final String QUEUE_CONTACT = "queue.contact";

    private static final String ROUTING_KEY_CONTACT = "routingKey.contact";

    private static final String DLQ_ROUTING_KEY_CONTACT = ROUTING_KEY_CONTACT+".dlq";

    @Bean("topicExchenge")
    public TopicExchange topicExchenge() {
        return ExchangeBuilder.topicExchange("topic.contact")
                .build();
    }

    @Bean("queueContact")
    Queue queueContact() {
        return QueueBuilder
                .durable(QUEUE_CONTACT)
                .deadLetterExchange(topicExchenge().getName())
                .deadLetterRoutingKey(DLQ_ROUTING_KEY_CONTACT)
                .build();
    }

    @Bean("queueContactDlq")
    public Queue queueContactDlq() {
        return QueueBuilder
                .durable(QUEUE_CONTACT+".dlq")
                .build();
    }

    @Bean("bindingQueueContact")
    Binding bindingQueueContact() {
        return BindingBuilder
                .bind(queueContact())
                .to(topicExchenge())
                .with(ROUTING_KEY_CONTACT);
    }

    @Bean("bindingQueueContactDlq")
    Binding bindingQueueContactDlq() {
        return BindingBuilder
                .bind(queueContactDlq())
                .to(topicExchenge())
                .with(DLQ_ROUTING_KEY_CONTACT);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

}
