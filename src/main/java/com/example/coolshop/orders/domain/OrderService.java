package com.example.coolshop.orders.domain;

import com.example.coolshop.orders.domain.exceptions.CustomerDoesNotExistException;
import com.example.coolshop.orders.domain.model.OrderDomain;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public Optional<OrderDomain> getOrder(Long id) {
        return orderRepository.getById(id);
    }

    public OrderDomain registerOrder(OrderDomain domain) {
        if (customerRepository.getById(domain.getCustomerId()).isPresent()) {
            return orderRepository.store(domain);
        } else {
            throw new CustomerDoesNotExistException(domain.getCustomerId());
        }
    }
}
