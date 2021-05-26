package com.example.coolshop.orders.domain;

import com.example.coolshop.orders.domain.model.OrderDomain;
import org.springframework.stereotype.Service;

@Service
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
