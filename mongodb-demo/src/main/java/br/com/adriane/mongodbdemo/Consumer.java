package br.com.adriane.mongodbdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Consumer {

    private final ObjectMapper objectMapper;
    private final PersonRepository repository;

    @KafkaListener(topics = "person-topic")
    public void processMessage(String person) throws JsonProcessingException {
        log.info("Consumindo evento de Pessoa = {}", person);
        Person personObj = objectMapper.readValue(person, Person.class);
        repository.save(personObj);
        log.info("Evento de Pessoa consumido com sucesso.");
    }

}
