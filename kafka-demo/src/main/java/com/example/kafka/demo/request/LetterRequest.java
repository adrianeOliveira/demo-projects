package com.example.kafka.demo.request;

import java.io.Serializable;

public class LetterRequest implements Serializable {
    String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
