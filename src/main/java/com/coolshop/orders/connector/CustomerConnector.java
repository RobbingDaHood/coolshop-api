package com.coolshop.orders.connector;

import com.coolshop.customers.domain.CustomerService;
import com.coolshop.orders.connector.mappers.CustomerMapper;
import com.coolshop.orders.domain.CustomerPlugIn;
import com.coolshop.orders.domain.model.CustomerDomain;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomerConnector implements CustomerPlugIn {
    private final CustomerService customerService;

    public CustomerConnector(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public Optional<CustomerDomain> getById(Long id) {
            return customerService.getCustomer(id)
                    .map(CustomerMapper::mapToDomain);
    }
}
