package br.com.adriane.springbatch;

import br.com.adriane.springbatch.event.PersonEvent;
import lombok.RequiredArgsConstructor;
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
        Properties props = new Properties();
        props.putAll(kafkaProperties.getProperties());

        return new KafkaItemReaderBuilder<String, PersonEvent>()
                .name("kafkaItemReader")
                .consumerProperties(props)
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

}
