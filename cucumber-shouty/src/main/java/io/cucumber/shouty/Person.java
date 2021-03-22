package io.cucumber.shouty;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private List<String> messageHeard = new ArrayList<>();
    private final Network network;

    public Person(final Network network) {
        this.network = network;
        network.subscribe(this);
    }

    public void shout(final String message) {
        network.broadcast(message);
    }

    public void hear(final String message) {
        messageHeard.add(message);
    }

    public List<String> getMessageHeard() {
        return messageHeard;
    }

    public void moveTo(int location) {
    }
}
