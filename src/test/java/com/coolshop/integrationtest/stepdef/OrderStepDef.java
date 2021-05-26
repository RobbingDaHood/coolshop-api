package com.coolshop.integrationtest.stepdef;

import com.coolshop.integrationtest.CucumberSpringConfiguration;
import com.coolshop.integrationtest.stepdef.models.OrderIntegrationTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderStepDef extends CucumberSpringConfiguration {

    private OrderIntegrationTest registeredOrderRepresentation;
    private OrderIntegrationTest fetchedOrderRepresentation;
    private OrderIntegrationTest requestCreatedOrderRepresentation;
    private List<List<OrderIntegrationTest>> listOfOrdersBelongingToCustomer;
    private World world;

    public OrderStepDef(World world) {
        this.world = world;
        this.requestCreatedOrderRepresentation = OrderIntegrationTest.builder()
                .itemIds(List.of(22L, 23L))
                .discount(200)
                .customerId(world.getRegisteredCustomerRepresentation().getId())
                .build();
        if (listOfOrdersBelongingToCustomer == null) {
            listOfOrdersBelongingToCustomer = new ArrayList<>();
        }
    }

    @When("^the client fetches the order$")
    public void the_client_issues_GET_order() {
        fetchedOrderRepresentation = HttpUtility.get(
                "http://localhost:8080/orders/" + registeredOrderRepresentation.getId(),
                "application/vnd.coolshop.v0.5+json")
                .bodyToFlux(OrderIntegrationTest.class)
                .blockFirst();
    }

    @Given("^the client creates new order$")
    public void the_client_issues_POST_order() {
        registeredOrderRepresentation = HttpUtility.post(
                requestCreatedOrderRepresentation,
                "http://localhost:8080/orders",
                "application/vnd.coolshop.v0.5+json")
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

    @Given("^the client fetches all orders belonging to the Customer$")
    public void the_client_issues_GET_ordersForCustomer() {
        listOfOrdersBelongingToCustomer.add(HttpUtility.get(
                "http://localhost:8080/orders?customerId=" + world.getRegisteredCustomerRepresentation().getId(),
                "application/vnd.coolshop.v0.5+json")
                .bodyToFlux(OrderIntegrationTest.class)
                .collectList()
                .block());
    }

    @Then("^the client have one more order$")
    public void the_client_issues_GET_checkorders() {
        int latest = listOfOrdersBelongingToCustomer.get(listOfOrdersBelongingToCustomer.size() - 1).size();
        int theOneBefore = listOfOrdersBelongingToCustomer.get(listOfOrdersBelongingToCustomer.size() - 2).size();
        assertEquals(1, latest-theOneBefore);
    }
}
