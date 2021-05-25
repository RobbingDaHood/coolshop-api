package com.example.coolshopapi.customerapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerExposure {

    @GetMapping("/customers/{customerId}")
    public String getCustomer(@PathVariable(value = "customerId") String customerId) {
        return String.format("Hello %s!", customerId);
    }
}
