package br.com.adriane.demo.reactiveprog.amqp;

import lombok.Data;

@Data
public class ContactMessage {
    private int id;
    private String name;
    private String email;
}
