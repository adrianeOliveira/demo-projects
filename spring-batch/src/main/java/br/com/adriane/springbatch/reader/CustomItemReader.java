package br.com.adriane.springbatch.reader;

import br.com.adriane.springbatch.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RequiredArgsConstructor
public class CustomItemReader implements ItemReader<Person> {

    private final AtomicLong randomId;

    @Override
    public Person read() {
        if (randomId.incrementAndGet() < 1_000) {
            String name = Math.random() > .5 ? "Maria" : "João";
            return new Person(randomId.toString(), name, name.toLowerCase().concat("@email.com"));
        }
        return null;
    }
}
