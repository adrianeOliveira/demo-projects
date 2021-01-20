package br.com.adriane.demo.kotlinkafka.producers

import br.com.adriane.demo.avro.Contato
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {
    private val log = LoggerFactory.getLogger(KafkaProducer::class.java)

    fun produce(message : String) {
        log.info("Sending message to topic ${kafkaTemplate.defaultTopic}")
        kafkaTemplate.sendDefault(UUID.randomUUID().toString(), message)
    }
}

@Component
class AvroProducer(
    private val kafkaTemplate: KafkaTemplate<String,Contato>
) {
    private val log = LoggerFactory.getLogger(AvroProducer::class.java)

    fun produce(contato: Contato) {
        log.info("Sending message contato = $contato")
        kafkaTemplate.send("topic-kotlin-avro", UUID.randomUUID().toString(), contato)
    }
}