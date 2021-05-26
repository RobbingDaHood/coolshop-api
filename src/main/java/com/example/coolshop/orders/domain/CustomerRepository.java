package com.example.coolshop.orders.domain;

import com.example.coolshop.orders.domain.model.CustomerDomain;

import java.util.Optional;

public interface CustomerRepository {
    Optional<CustomerDomain> getById(Long id);
}
