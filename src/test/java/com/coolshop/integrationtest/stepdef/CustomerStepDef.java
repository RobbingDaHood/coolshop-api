package com.coolshop.integrationtest.stepdef;

import com.coolshop.integrationtest.stepdef.models.CustomerIntegrationTest;
import com.coolshop.integrationtest.CucumberSpringConfiguration;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerStepDef extends CucumberSpringConfiguration {

    private CustomerIntegrationTest fetchedCustomerRepresentation;
    private World world;

    public CustomerStepDef(World world) {
        this.world = world;
    }

    @When("^the client fetches the Customer$")
    public void the_client_issues_GET_customer() {
        fetchedCustomerRepresentation = HttpUtility.get(
                "http://localhost:8080/customers/" + world.getRegisteredCustomerRepresentation().getId())
                .bodyToFlux(CustomerIntegrationTest.class)
                .blockFirst();
    }

    @Given("the client creates new customer with name {string}")
    public void the_client_issues_POST_customer(String name) {
        CustomerIntegrationTest body = CustomerIntegrationTest.builder()
                .fullName(name)
                .build();

        world.setRegisteredCustomerRepresentation(HttpUtility.post(body, "http://localhost:8080/customers")
                .bodyToFlux(CustomerIntegrationTest.class)
                .blockFirst());
    }

    @Then("the registered customer has the name {string}")
    public void the_client_registered_customer_has_same_name(String name) {
        Assertions.assertEquals(name, world.getRegisteredCustomerRepresentation().getFullName());
    }

    @Then("the fetched customer has the name {string}")
    public void the_client_fetched_customer_has_same_name(String name) {
        assertEquals(name, fetchedCustomerRepresentation.getFullName());
    }
}
