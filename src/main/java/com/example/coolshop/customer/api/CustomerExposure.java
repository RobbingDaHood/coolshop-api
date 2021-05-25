package com.example.coolshop.customer.api;

import com.example.coolshop.customer.api.representation.Customer;
import com.example.coolshop.customer.domain.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerExposure {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable(value = "customerId") String customerId) {
        return new Customer(customerService.getCustomer(customerId).getId());
    }
}
