package br.com.adriane.springbatch;

import br.com.adriane.springbatch.entities.Person;
import br.com.adriane.springbatch.listener.StepStatisticsListener;
import br.com.adriane.springbatch.processor.PersonProcessor;
import br.com.adriane.springbatch.writer.CustomMongoItemWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
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
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.kafka.KafkaItemReader;
import org.springframework.batch.item.kafka.builder.KafkaItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job(Step step) {
        return jobBuilderFactory.get("job")
            .incrementer(new RunIdIncrementer())
            .start(step)
            .build();
    }

    @Bean
    public Step step(KafkaItemReader<String, String> reader, CustomMongoItemWriter writer, StepStatisticsListener listener, PersonProcessor processor) {
        return stepBuilderFactory.get("step")
            .<String, Person>chunk(3)
            .reader(reader)
            .processor(processor)
            .writer(writer)
            .listener(listener)
            .allowStartIfComplete(true)
            .build();
    }

    @Bean
    public KafkaItemReader<String, String> personItemReader() {
        return new KafkaItemReaderBuilder<String, String>()
            .name("kafkaItemReader")
            .consumerProperties(buildKafkaProperties())
            .partitionOffsets(new HashMap<>())
            .partitions(0,1,2)
            .topic("person-topic")
            .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    private Properties buildKafkaProperties() {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "person-group");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return properties;
    }

}
