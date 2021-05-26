package com.coolshop.customer.domain;

import com.coolshop.customer.domain.model.CustomerDomain;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerPlugIn customerPlugIn;

    public CustomerService(CustomerPlugIn customerPlugIn) {
        this.customerPlugIn = customerPlugIn;
    }

    public Optional<CustomerDomain> getCustomer(Long id) {
        return customerPlugIn.getById(id);
    }

    public CustomerDomain registerCustomer(CustomerDomain customerDomain) {
        return customerPlugIn.store(customerDomain);
    }
}
