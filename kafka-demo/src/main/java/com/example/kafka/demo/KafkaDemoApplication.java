package com.example.kafka.demo;

import com.example.kafka.demo.channel.AvroDemoChannel;
import com.example.kafka.demo.channel.DemoChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding({DemoChannel.class, AvroDemoChannel.class})
public class KafkaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaDemoApplication.class, args);
	}

}
