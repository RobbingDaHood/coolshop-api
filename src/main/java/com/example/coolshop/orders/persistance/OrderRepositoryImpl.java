package com.example.coolshop.orders.persistance;

import com.example.coolshop.orders.domain.OrderRepository;
import com.example.coolshop.orders.domain.model.OrderDomain;
import com.example.coolshop.orders.persistance.mappers.OrderMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderCrudRepository orderCrudRepository;

    public OrderRepositoryImpl(OrderCrudRepository customerCrudRepository) {
        this.orderCrudRepository = customerCrudRepository;
    }

    @Override
    public Optional<OrderDomain> getById(Long id) {
        return orderCrudRepository.findById(id)
                .map(OrderMapper::mapToDomain);
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
