package br.com.adriane.demo.kotlinkafka

import br.com.adriane.demo.kotlinkafka.consumers.KafkaConsumer
import br.com.adriane.demo.kotlinkafka.producers.KafkaProducer
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.annotation.DirtiesContext


@SpringBootTest
@DirtiesContext //resetar o contexto para cada test
@EmbeddedKafka(partitions = 1, brokerProperties = ["listeners=PLAINTEXT://localhost:9092","port=9092"])
class KotlinKafkaApplicationTests(
	@Autowired
	private val consumer: KafkaConsumer,
	@Autowired
	private val producer: KafkaProducer
) {

	@Test
	fun `sending message to kafka and consumed with success`() {
		val payload = "Message to embedded kafka"
		producer.produce(payload)
		Thread.sleep(1000)
		assertThat(consumer.message, containsString(payload))
	}

}
