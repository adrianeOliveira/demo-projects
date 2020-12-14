package com.example.kafka.demo.consumer;

import com.example.kafka.avro.Letter;
import com.example.kafka.demo.channel.AvroDemoChannel;
import com.example.kafka.demo.channel.DemoChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DemoConsumer {

    @StreamListener(DemoChannel.CREATED_DEMO_CHANNEL)
    public void receive(final String payload) {
        log.info("received payload={}", payload);
    }

    @StreamListener(AvroDemoChannel.CREATED_AVRO_DEMO_CHANNEL)
    public void receiveWithAvroSchema(final Letter payload) {
        log.info("received payload={}",payload);
    }
}
