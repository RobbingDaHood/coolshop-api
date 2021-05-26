package com.example.coolshop.customer.domain;

import com.example.coolshop.customer.domain.model.CustomerDomain;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDomain getCustomer(Long id) {
        return customerRepository.getById(id);
    }

    public CustomerDomain registerCustomer(CustomerDomain customerDomain) {
        return customerRepository.store(customerDomain);
    }
}
