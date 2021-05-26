package com.example.coolshop.integrationtest.stepdef;

import com.example.coolshop.customer.api.representation.CustomerRepresentation;
import com.example.coolshop.integrationtest.stepdef.models.CustomerIntegrationTest;
import io.cucumber.java.en.When;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CustomerStepDef extends CucumberSpringConfiguration {

    private CustomerIntegrationTest registeredCustomerRepresentation;

    @When("^the client calls /version$")
    public void the_client_issues_GET_customer() throws Throwable {
//        executeGet("http://localhost:8080/version");

        WebClient client = WebClient.create("http://localhost:8080/customers/22");
    }

    @When("^the client creates new customer$")
    public void the_client_issues_POST_customer() throws Throwable {
        CustomerIntegrationTest body = CustomerIntegrationTest.builder()
                .fullName("Jørgen Petersen")
                .build();

        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofMillis(5000))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

        registeredCustomerRepresentation = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl("http://localhost:8080/customers")
                .build()
                .post()
                .body(BodyInserters.fromValue(body))
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToMono(CustomerIntegrationTest.class)
                .block();
    }
}
