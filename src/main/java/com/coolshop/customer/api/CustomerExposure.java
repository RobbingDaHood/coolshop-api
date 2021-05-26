package com.coolshop.customer.api;

import com.coolshop.customer.api.exceptions.CustomerDoesNotExistException;
import com.coolshop.customer.api.mappers.CustomerMapper;
import com.coolshop.customer.api.representation.CustomerRepresentation;
import com.coolshop.customer.domain.CustomerService;
import com.coolshop.customer.domain.model.CustomerDomain;
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
    public CustomerRepresentation postCustomer(@RequestBody CustomerRepresentation customerRepresentation) {
        return CustomerMapper.mapFromDomain(
                customerService.registerCustomer(
                        CustomerMapper.mapToDomain(customerRepresentation)
                )
        );
    }
}
