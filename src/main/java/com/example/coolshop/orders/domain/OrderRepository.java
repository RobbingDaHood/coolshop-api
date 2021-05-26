package com.example.coolshop.orders.domain;

import com.example.coolshop.orders.domain.model.OrderDomain;

import java.util.Optional;

public interface OrderRepository {
    Optional<OrderDomain> getById(Long id);
    OrderDomain store(OrderDomain domain);
}
