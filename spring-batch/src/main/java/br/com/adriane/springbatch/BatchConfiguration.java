package br.com.adriane.springbatch;

import br.com.adriane.springbatch.event.PersonEvent;
import java.util.Properties;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.kafka.KafkaItemReader;
import org.springframework.batch.item.kafka.builder.KafkaItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableKafka
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final JobRepository jobRepository;

    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job job(Step step) {
        return jobBuilderFactory.get("job")
            .start(step)
            .build();
    }

    @Bean
    public KafkaItemReader<String, String> personItemReader() {

        return new KafkaItemReaderBuilder<String, String>()
                .name("kafkaItemReader")
                .consumerProperties(buildKafkaProperties())
                .partitions(0)
                .topic("person-topic")
                .build();
    }

    @Bean
    public MongoItemWriter<PersonEvent> writer(MongoOperations template) {
        return new MongoItemWriterBuilder<PersonEvent>()
                .collection("job-batch")
                .template(template)
                .build();
    }

    @Bean
    public Step step(KafkaItemReader<String, String> reader, MongoItemWriter<PersonEvent> writer) {
        return stepBuilderFactory.get("step")
            .<String, PersonEvent>chunk(10)
            .reader(reader)
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
