package com.coolshop.customers.domain;

import com.coolshop.customers.domain.model.CustomerDomain;

import java.util.Optional;

public interface CustomerPlugIn {
    Optional<CustomerDomain> getById(Long id);
    CustomerDomain store(CustomerDomain domain);
}
