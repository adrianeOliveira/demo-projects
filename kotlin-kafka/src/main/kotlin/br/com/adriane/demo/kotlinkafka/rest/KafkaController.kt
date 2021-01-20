package br.com.adriane.demo.kotlinkafka.rest

import br.com.adriane.demo.avro.Contato
import br.com.adriane.demo.kotlinkafka.producers.AvroProducer
import br.com.adriane.demo.kotlinkafka.producers.KafkaProducer
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/kafka")
class KafkaController(
    private val kafkaProducer: KafkaProducer,
    private val avroProducer: AvroProducer

) {
    private val log = LoggerFactory.getLogger(KafkaController::class.java)

    @GetMapping
    fun triggerMessageToKafka() {
        log.info("sending message to kafka")
        kafkaProducer.produce("Hello Kafka")
    }

    @GetMapping("/contato")
    fun triggerAvroMessage() {
        log.info("Sending message using schema registry with avro")

        val contato = Contato.newBuilder()
            .setNome("Adriane")
            .setEmail("email@adriane")
            .build()

       avroProducer.produce(contato)
    }
}