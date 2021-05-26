package com.example.coolshop.customer.domain;

import com.example.coolshop.customer.domain.model.CustomerDomain;

import java.util.Optional;

public interface CustomerRepository {
    Optional<CustomerDomain> getById(Long id);
    CustomerDomain store(CustomerDomain domain);
}
