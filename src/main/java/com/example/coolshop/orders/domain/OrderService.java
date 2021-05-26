package com.example.coolshop.orders.domain;

import com.example.coolshop.orders.domain.model.OrderDomain;

public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDomain getOrder(Long id) {
        return orderRepository.getById(id);
    }

    public OrderDomain registerOrder(OrderDomain domain) {
        return orderRepository.store(domain);
    }
}
