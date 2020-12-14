package com.example.kafka.demo.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface AvroDemoChannel {
    String CREATED_AVRO_DEMO_CHANNEL = "demo-avro-topic-input";

    @Output(CREATED_AVRO_DEMO_CHANNEL)
    MessageChannel output();
}
