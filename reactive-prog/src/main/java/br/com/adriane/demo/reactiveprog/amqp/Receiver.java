package br.com.adriane.demo.reactiveprog.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Receiver {

    @RabbitListener(queues = {"queue.contact"})
    public void receiveMessage(ContactMessage msg) {
      log.info("Mensagem >> {}", msg);
    }
}
