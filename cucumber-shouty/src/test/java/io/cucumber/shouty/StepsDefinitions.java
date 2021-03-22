package io.cucumber.shouty;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;

public class StepsDefinitions {

    private Person sean;

    private Person lucy;

    private String messageFromSean;

    @Given("Lucy is located {int} metres from Sean")
    public void lucyIsLocatedMetresFromSean(int distance) {
        final Network network = new Network();
        sean = new Person(network);
        lucy = new Person(network);
        lucy.moveTo(distance);
    }

    @When("Sean shouts {string}")
    public void seanShouts(String message) {
        sean.shout(message);
        messageFromSean = message;
    }

    @Then("Lucy hears Sean's message")
    public void lucyHearsSeanSMessage() {
        assertEquals(Arrays.asList(messageFromSean), lucy.getMessageHeard());
    }
}
