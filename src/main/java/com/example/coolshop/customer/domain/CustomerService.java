package com.example.coolshop.customer.domain;

import com.example.coolshop.customer.domain.model.CustomerDomain;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<CustomerDomain> getCustomer(Long id) {
        return customerRepository.getById(id);
    }

    public CustomerDomain registerCustomer(CustomerDomain customerDomain) {
        return customerRepository.store(customerDomain);
    }
}
