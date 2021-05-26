package com.example.coolshop.orders.domain;

import com.example.coolshop.orders.domain.model.OrderDomain;

public interface OrderRepository {
    OrderDomain getById(Long id);

    OrderDomain store(OrderDomain domain);
}
