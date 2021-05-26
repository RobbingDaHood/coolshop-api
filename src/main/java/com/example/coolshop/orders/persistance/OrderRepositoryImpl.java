package com.example.coolshop.orders.persistance;

import com.example.coolshop.orders.domain.OrderRepository;
import com.example.coolshop.orders.domain.model.OrderDomain;
import com.example.coolshop.orders.persistance.mappers.OrderMapper;

public class OrderRepositoryImpl implements OrderRepository {
    private final OrderCrudRepository orderCrudRepository;

    public OrderRepositoryImpl(OrderCrudRepository customerCrudRepository) {
        this.orderCrudRepository = customerCrudRepository;
    }

    @Override
    public OrderDomain getById(Long id) {
        return orderCrudRepository.findById(id)
                .map(OrderMapper::mapToDomain)
                .get();
    }

    @Override
    public OrderDomain store(OrderDomain domain) {
        return OrderMapper.mapToDomain(
                orderCrudRepository.save(
                        OrderMapper.mapFromDomain(domain)
                )
        );
    }
}
