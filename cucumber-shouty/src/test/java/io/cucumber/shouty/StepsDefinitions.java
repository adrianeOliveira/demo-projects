package io.cucumber.shouty;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;

public class StepsDefinitions {

    private Person sean;

    private Person lucy;

    private String messageFromSean;
    private Network network;

    @Before
    public void createNetwork() {
        network = new Network();
    }

    @When("Sean shouts {string}")
    public void seanShouts(String message) {
        sean.shout(message);
        messageFromSean = message;
    }

    @Then("Lucy hears Sean's message")
    public void lucyHearsSeanSMessage() {
        assertEquals(Arrays.asList(messageFromSean), lucy.getMessagesHeard());
    }

    @Given("a person named Lucy")
    public void aPersonNamedLucy() {
        lucy = new Person(network);
    }

    @And("a person named Sean")
    public void aPersonNamedSean() {
        sean = new Person(network);
    }
}
