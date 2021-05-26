package com.coolshop.orders.domain;

import com.coolshop.orders.domain.model.CustomerDomain;

import java.util.Optional;

public interface CustomerPlugIn {
    Optional<CustomerDomain> getById(Long id);
}
