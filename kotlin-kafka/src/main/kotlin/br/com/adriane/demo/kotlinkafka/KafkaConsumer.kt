package br.com.adriane.demo.kotlinkafka

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumer {

    private val log = LoggerFactory.getLogger(KafkaConsumer::class.java)

    @KafkaListener(topics = ["topic-kotlin-app"], groupId = "kotlin-group")
    fun receiveMessage(message : String) {
        log.info("Message received = $message")
    }
}