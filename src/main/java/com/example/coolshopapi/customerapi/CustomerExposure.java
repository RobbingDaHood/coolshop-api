package com.example.coolshopapi.customerapi;

import com.example.coolshopapi.customerapi.representation.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerExposure {

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable(value = "customerId") String customerId) {
        return new Customer(customerId);
    }
}
