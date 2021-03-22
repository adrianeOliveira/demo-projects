package io.cucumber.shouty;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static java.util.Arrays.asList;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;

public class StepsDefinitions {

    private String messageFromSean;

    private Network network;

    private HashMap<String, Person> people;

    @Before
    public void createNetwork() {
        network = new Network();
        people = new HashMap<>();
    }

    @Given("a person named {word}")
    public void aPersonNamed(String name) {
        people.put(name, new Person(network));
    }

    @When("Sean shouts {string}")
    public void seanShouts(String message) {
        people.get("Sean").shout(message);
        messageFromSean = message;
    }

    @Then("Lucy should hear Sean's message")
    public void lucyHearsSeansMessage()  {
        assertEquals(asList(messageFromSean), people.get("Lucy").getMessagesHeard());
    }

}
