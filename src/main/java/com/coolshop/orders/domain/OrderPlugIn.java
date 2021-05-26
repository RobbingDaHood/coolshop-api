package com.coolshop.orders.domain;

import com.coolshop.orders.domain.model.OrderDomain;

import java.util.List;
import java.util.Optional;

public interface OrderPlugIn {
    Optional<OrderDomain> getById(Long id);
    OrderDomain store(OrderDomain domain);
    List<OrderDomain> getAllByCustomerID(Long customerId);
}
