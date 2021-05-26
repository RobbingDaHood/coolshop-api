package com.example.coolshop.integrationtest.stepdef;

import com.example.coolshop.integrationtest.CucumberSpringConfiguration;
import com.example.coolshop.integrationtest.stepdef.models.OrderIntegrationTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderStepDef extends CucumberSpringConfiguration {

    private OrderIntegrationTest registeredOrderRepresentation;
    private OrderIntegrationTest fetchedOrderRepresentation;
    private OrderIntegrationTest requestCreatedOrderRepresentation;
    private World world;

    public OrderStepDef(World world) {
        this.world = world;
        this.requestCreatedOrderRepresentation = OrderIntegrationTest.builder()
                .itemIds(List.of(22L, 23L))
                .discount(200)
                .customerId(world.getRegisteredCustomerRepresentation().getId())
                .build();
    }

    @When("^the client fetches the order$")
    public void the_client_issues_GET_order() {
        fetchedOrderRepresentation = HttpUtility.get("http://localhost:8080/orders/" + registeredOrderRepresentation.getId())
                .bodyToFlux(OrderIntegrationTest.class)
                .blockFirst();
    }

    @Given("^the client creates new order$")
    public void the_client_issues_POST_order() {
        registeredOrderRepresentation = HttpUtility.post(requestCreatedOrderRepresentation, "http://localhost:8080/orders")
                .bodyToFlux(OrderIntegrationTest.class)
                .blockFirst();
    }

    @Then("^the registered order is correct$")
    public void the_client_registered_customer_has_same_name() {
        assertEquals(requestCreatedOrderRepresentation.getDiscount(), registeredOrderRepresentation.getDiscount());
        assertEquals(requestCreatedOrderRepresentation.getCustomerId(), registeredOrderRepresentation.getCustomerId());
        assertEquals(requestCreatedOrderRepresentation.getItemIds(), registeredOrderRepresentation.getItemIds());
    }

    @Then("^the fetched order is correct$")
    public void the_client_fetched_customer_has_same_name() {
        assertEquals(requestCreatedOrderRepresentation.getDiscount(), fetchedOrderRepresentation.getDiscount());
        assertEquals(requestCreatedOrderRepresentation.getCustomerId(), fetchedOrderRepresentation.getCustomerId());
        assertEquals(requestCreatedOrderRepresentation.getItemIds(), fetchedOrderRepresentation.getItemIds());
    }
}
