package br.com.adriane.springbatch;

import br.com.adriane.springbatch.entities.Person;
import br.com.adriane.springbatch.listener.StepStatisticsListener;
import br.com.adriane.springbatch.processor.PersonProcessor;
import br.com.adriane.springbatch.writer.CustomMongoItemWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.Future;
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
import org.springframework.batch.integration.async.AsyncItemProcessor;
import org.springframework.batch.integration.async.AsyncItemWriter;
import org.springframework.batch.item.kafka.KafkaItemReader;
import org.springframework.batch.item.kafka.builder.KafkaItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
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
            .start(step).on("*")
            .end().end()
            .build();
    }

    @Bean
    public Step step(TaskExecutor taskExecutor, KafkaItemReader<String, String> reader, AsyncItemWriter<Person> writer, StepStatisticsListener listener, AsyncItemProcessor<String, Person> processor) {
        return stepBuilderFactory.get("step")
            .<String, Future<Person>>chunk(6)
            .reader(reader)
            .processor(processor)
            .writer(writer)
            .listener(listener)
            .allowStartIfComplete(true)
            .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        /*ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(3);
        taskExecutor.setCorePoolSize(3);
        taskExecutor.setQueueCapacity(3);
        taskExecutor.afterPropertiesSet()*/
        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor("asyncExecutor_");
        taskExecutor.setConcurrencyLimit(3);
        return taskExecutor;
    }

    @Bean
    public AsyncItemProcessor<String, Person> processor(TaskExecutor taskExecutor, PersonProcessor itemProcessor) {
        AsyncItemProcessor<String, Person> asyncItemProcessor = new AsyncItemProcessor();
        asyncItemProcessor.setTaskExecutor(taskExecutor);
        asyncItemProcessor.setDelegate(itemProcessor);
        return asyncItemProcessor;
    }

    @Bean
    public AsyncItemWriter<Person> writer(CustomMongoItemWriter itemWriter) {
        AsyncItemWriter<Person>  asyncItemWriter = new AsyncItemWriter();
        asyncItemWriter.setDelegate(itemWriter);
        return asyncItemWriter;
    }

    @Bean
    public KafkaItemReader<String, String> kafkaItemReader() {
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
