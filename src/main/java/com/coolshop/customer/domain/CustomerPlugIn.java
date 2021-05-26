package com.coolshop.customer.domain;

import com.coolshop.customer.domain.model.CustomerDomain;

import java.util.Optional;

public interface CustomerPlugIn {
    Optional<CustomerDomain> getById(Long id);
    CustomerDomain store(CustomerDomain domain);
}
