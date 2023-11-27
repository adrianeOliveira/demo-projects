package br.com.adriane.springbatch.processor;

import br.com.adriane.springbatch.Person;
import br.com.adriane.springbatch.event.PersonEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersonProcessor implements ItemProcessor<String, Person> {

    private final ObjectMapper objectMapper;

    @Override
    public Person process(String item) throws Exception {
        PersonEvent personEvent = objectMapper.readValue(item, PersonEvent.class);
        log.info("Inciando processor com item {}", item);
        Person person = new Person();
        person.setEmail(personEvent.getEmail());
        person.setName(personEvent.getName());
        log.info("Processor finalizado");
        return person;
    }

}
