package br.com.adriane.demo.reactiveprog.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Receiver {

    @RabbitListener(queues = {"queue.contact"})
    @Retryable(
            backoff = @Backoff(delay = 900L, multiplier = 2, maxDelay = 1800L),
            value = {FooForRetryException.class})
    public void receiveMessage(ContactMessage msg) {
      log.info("Mensagem >> {}", msg);
      throw new FooForRetryException("Retry exception");// linha pra testar o retry antes de mandar pra dlq
    }
}
