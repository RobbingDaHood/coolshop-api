package com.example.coolshop.customer.api;

import com.example.coolshop.customer.api.exceptions.CustomerDoesNotExistException;
import com.example.coolshop.customer.api.mappers.CustomerMapper;
import com.example.coolshop.customer.api.representation.CustomerRepresentation;
import com.example.coolshop.customer.domain.CustomerService;
import com.example.coolshop.customer.domain.model.CustomerDomain;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CustomerExposure {

    private final CustomerService customerService;

    public CustomerExposure(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers/{customerId}")
    public CustomerRepresentation getCustomer(@PathVariable(value = "customerId") Long customerId) {
        Optional<CustomerDomain> customer = customerService.getCustomer(customerId);

        if (customer.isPresent()) {
            return CustomerMapper.mapFromDomain(customer.get());
        } else {
            throw new CustomerDoesNotExistException(customerId);
        }
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
