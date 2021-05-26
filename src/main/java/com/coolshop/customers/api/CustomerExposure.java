package com.coolshop.customers.api;

import com.coolshop.customers.api.exceptions.CustomerDoesNotExistException;
import com.coolshop.customers.api.mappers.CustomerMapper;
import com.coolshop.customers.api.representation.CustomerRepresentation;
import com.coolshop.customers.domain.CustomerService;
import com.coolshop.customers.domain.model.CustomerDomain;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CustomerExposure {

    private final CustomerService customerService;

    public CustomerExposure(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/customers/{customerId}",
            produces = "application/vnd.coolshop.v1.0+json;charset=UTF-8")
    public CustomerRepresentation getCustomer(@PathVariable(value = "customerId") Long customerId) {
        Optional<CustomerDomain> customer = customerService.getCustomer(customerId);

        if (customer.isPresent()) {
            return CustomerMapper.mapFromDomain(customer.get());
        } else {
            throw new CustomerDoesNotExistException(customerId);
        }
    }

    @PostMapping(path = "/customers",
            produces = "application/vnd.coolshop.v1.0+json;charset=UTF-8",
            consumes = "application/vnd.coolshop.v1.0+json;charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerRepresentation postCustomer(@RequestBody CustomerRepresentation customerRepresentation) {
        return CustomerMapper.mapFromDomain(
                customerService.registerCustomer(
                        CustomerMapper.mapToDomain(customerRepresentation)
                )
        );
    }
}
