package br.com.adriane.demo.kotlinkafka.consumers

import br.com.adriane.demo.avro.Contato
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumer {

    private val log = LoggerFactory.getLogger(KafkaConsumer::class.java)

    var message : String? = null

    @KafkaListener(topics = ["topic-kotlin-app"], groupId = "kotlin-group")
    fun receiveMessage(message : String) {
        log.info("Message received = $message")
        this.message = message
    }

    @KafkaListener(topics = ["topic-kotlin-avro"], groupId = "kotlin-avro-group")
    fun receiveAvroMessage(contato: Contato) {
        log.info("Avro message received = $contato")
    }
}