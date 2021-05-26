package com.example.coolshop.integrationtest.stepdef;

import com.example.coolshop.integrationtest.CucumberSpringConfiguration;
import com.example.coolshop.integrationtest.stepdef.models.CustomerIntegrationTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerStepDef extends CucumberSpringConfiguration {

    private CustomerIntegrationTest registeredCustomerRepresentation;
    private CustomerIntegrationTest fetchedCustomerRepresentation;

    @When("^the client fetches the Customer$")
    public void the_client_issues_GET_customer() {
        fetchedCustomerRepresentation = HttpUtility.get("http://localhost:8080/customers/" + registeredCustomerRepresentation.getId())
                .bodyToFlux(CustomerIntegrationTest.class)
                .blockFirst();
    }

    @Given("the client creates new customer with name {string}")
    public void the_client_issues_POST_customer(String name) {
        CustomerIntegrationTest body = CustomerIntegrationTest.builder()
                .fullName(name)
                .build();

        registeredCustomerRepresentation = HttpUtility.post(body, "http://localhost:8080/customers")
                .bodyToFlux(CustomerIntegrationTest.class)
                .blockFirst();
    }

    @Then("the registered customer has the name {string}")
    public void the_client_registered_customer_has_same_name(String name) {
        assertEquals(name, registeredCustomerRepresentation.getFullName());
    }

    @Then("the fetched customer has the name {string}")
    public void the_client_fetched_customer_has_same_name(String name) {
        assertEquals(name, fetchedCustomerRepresentation.getFullName());
    }
}
