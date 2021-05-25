package com.example.coolshop.customer.api;

import com.example.coolshop.customer.api.mappers.CustomerMapper;
import com.example.coolshop.customer.api.representation.Customer;
import com.example.coolshop.customer.domain.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerExposure {

    private final CustomerService customerService;

    public CustomerExposure(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable(value = "customerId") String customerId) {
        return CustomerMapper.mapFromDomain(customerService.getCustomer(customerId));
    }
}
