package br.com.adriane.mongodbdemo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
public class Person {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    public Person() {
    }

}
