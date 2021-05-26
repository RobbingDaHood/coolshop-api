package com.example.coolshop.customer.domain;

import com.example.coolshop.customer.domain.model.CustomerDomain;

public interface CustomerRepository {
    CustomerDomain getById(Long id);
    CustomerDomain store(CustomerDomain domain);
}
