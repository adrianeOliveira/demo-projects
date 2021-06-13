package br.com.adriane.demo.reactiveprog.controller;

import br.com.adriane.demo.reactiveprog.amqp.ContactMessage;
import br.com.adriane.demo.reactiveprog.amqp.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/contact")
public class ContactController {

    private final Producer producer;

    public ContactController(final Producer producer) {
        this.producer = producer;
    }

    @GetMapping
    public void sendMessage() {
        ContactMessage contactMessage = new ContactMessage();
        contactMessage.setId(1);
        contactMessage.setName("Adriane Rodrigues");
        contactMessage.setEmail("adriane@email.com.br");

        log.info("Sending... {}", contactMessage);
        producer.send(contactMessage);
    }
}
