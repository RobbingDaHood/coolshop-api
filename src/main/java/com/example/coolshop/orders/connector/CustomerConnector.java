package com.example.coolshop.orders.connector;

import com.example.coolshop.customer.domain.CustomerService;
import com.example.coolshop.orders.connector.mappers.CustomerMapper;
import com.example.coolshop.orders.domain.CustomerRepository;
import com.example.coolshop.orders.domain.model.CustomerDomain;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomerConnector implements CustomerRepository {
    private final CustomerService customerService;

    public CustomerConnector(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public Optional<CustomerDomain> getById(Long id) {
        return Optional.of(CustomerMapper.mapToDomain(customerService.getCustomer(id)));
    }
}
