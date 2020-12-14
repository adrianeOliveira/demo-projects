package com.example.kafka.demo.controller;

import com.example.kafka.avro.Letter;
import com.example.kafka.demo.channel.AvroDemoChannel;
import com.example.kafka.demo.channel.DemoChannel;
import com.example.kafka.demo.request.LetterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/send")
public class DemoController {

    private final DemoChannel demoChannel;

    private final AvroDemoChannel avroDemoChannel;

    public DemoController(final DemoChannel demoChannel,
                          final AvroDemoChannel avroDemoChannel) {
        this.demoChannel = demoChannel;
        this.avroDemoChannel = avroDemoChannel;
    }

    @GetMapping
    public ResponseEntity<?> kafkaProducerDemo() {
        demoChannel.info("Received GET request");
        final Message<String> message = MessageBuilder.withPayload("Hello demo project").build();
        demoChannel.info("sending payload={} ", message);
        demoChannel.output().send(message);
        demoChannel.info("message sent");
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> kafkaProducerDemoPost(@RequestBody LetterRequest vo){
        log.info("Received POST request with vo={}", vo);
        Letter letter = Letter.newBuilder().setValue(vo.getValue()).build();
        Message<Letter> message = MessageBuilder.withPayload(letter).build();
        log.info("Sending Avro Message={}", message.getPayload());
        avroDemoChannel.output().send(message);
        log.info("Avro Message sent");
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
