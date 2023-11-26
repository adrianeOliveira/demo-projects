package br.com.adriane.springbatch;

import br.com.adriane.springbatch.event.PersonEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.kafka.KafkaItemReader;
import org.springframework.batch.item.kafka.builder.KafkaItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Properties;

@Configuration
@EnableKafka
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfiguration {

    private final JobRepository jobRepository;

    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job job(final @Qualifier("personStep") Step step) {
        return new JobBuilder("job", jobRepository)
                .start(step)
                .build();
    }

    @Bean
    public KafkaItemReader<String, PersonEvent> personItemReader( KafkaProperties kafkaProperties) {

        return new KafkaItemReaderBuilder<String, PersonEvent>()
                .name("kafkaItemReader")
                .consumerProperties(buildKafkaProperties())
                .partitions(0)
                .topic("person-topic")
                .build();
    }

    @Bean
    public MongoItemWriter<PersonEvent> writer(MongoOperations template) {
        return new MongoItemWriterBuilder<PersonEvent>()
                .collection("spring-batch-kafka")
                .template(template)
                .build();
    }

    @Bean
    @Qualifier("personStep")
    public Step step(KafkaItemReader<String, PersonEvent> personItemReader, MongoItemWriter writer) {
        return new StepBuilder("step", jobRepository)
                .chunk(10, transactionManager)
                .repository(jobRepository)
                .reader(personItemReader)
                .writer(writer)
                .build();
    }

    private Properties buildKafkaProperties() {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "person-group");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return properties;
    }

}
