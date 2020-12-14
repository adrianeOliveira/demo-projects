package br.com.adrianerodrigues.spring.jdbc.demo;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@RequiredArgsConstructor
public class Person {
    @Id
    private long id;
    private String firstName;
    private String lastName;
}
