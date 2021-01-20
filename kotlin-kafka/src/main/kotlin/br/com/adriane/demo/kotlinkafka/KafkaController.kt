package br.com.adriane.demo.kotlinkafka

import br.com.adriane.demo.avro.Contato
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/kafka")
class KafkaController(
    val kafkaTemplate: KafkaTemplate<String,String>,
    val kafkaTemplateContato: KafkaTemplate<String, Contato>

) {
    private val log = LoggerFactory.getLogger(KafkaController::class.java)

    @GetMapping
    fun triggerMessageToKaf() {
        log.info("sending message to kafka")
        kafkaTemplate.send("topic-kotlin-app", "key", "Hello Kafka from Kotlin")
    }

    @GetMapping("/contato")
    fun triggerAvroMessage() {
        log.info("Sending message using schema registry with avro")

        val contato = Contato.newBuilder()
            .setNome("Adriane")
            .setEmail("email@adriane")
            .build()

        kafkaTemplateContato.send("topic-kotlin-avro", "avro-key", contato)
    }
}