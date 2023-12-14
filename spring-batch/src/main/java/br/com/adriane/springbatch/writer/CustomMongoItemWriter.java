package br.com.adriane.springbatch.writer;

import br.com.adriane.springbatch.entities.Person;
import br.com.adriane.springbatch.repositories.PersonRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomMongoItemWriter implements ItemWriter<Person> {

    private final PersonRepository repository;

    @Override
    public void write(List<? extends Person> items) {
        repository.saveAll(items);
    }

}
