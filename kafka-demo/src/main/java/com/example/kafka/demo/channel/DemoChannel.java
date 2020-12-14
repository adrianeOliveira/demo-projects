package com.example.kafka.demo.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface DemoChannel {
    String CREATED_DEMO_CHANNEL = "demo-topic-input";

    @Output(CREATED_DEMO_CHANNEL)
    MessageChannel output();
}
