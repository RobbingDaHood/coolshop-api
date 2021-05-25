package com.example.coolshop.customer.api;

import com.example.coolshop.customer.api.mappers.CustomerMapper;
import com.example.coolshop.customer.api.representation.CustomerRepresentation;
import com.example.coolshop.customer.domain.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerExposure {

    private final CustomerService customerService;

    public CustomerExposure(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers/{customerId}")
    public CustomerRepresentation getCustomer(@PathVariable(value = "customerId") String customerId) {
        return CustomerMapper.mapFromDomain(customerService.getCustomer(customerId));
    }

    @PostMapping("/customers")
    public CustomerRepresentation postCustomer(@RequestBody CustomerRepresentation customerRepresentation) {
        return CustomerMapper.mapFromDomain(
                customerService.registerCustomer(
                        CustomerMapper.mapToDomain(customerRepresentation)
                )
        );
    }
}
