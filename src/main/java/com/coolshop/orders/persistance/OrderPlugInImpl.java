package com.coolshop.orders.persistance;

import com.coolshop.orders.domain.OrderPlugIn;
import com.coolshop.orders.domain.model.OrderDomain;
import com.coolshop.orders.persistance.mappers.OrderMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class OrderPlugInImpl implements OrderPlugIn  {
    private final OrderCrudRepository orderCrudRepository;

    public OrderPlugInImpl(OrderCrudRepository customerCrudRepository) {
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

    @Override
    public List<OrderDomain> getAllByCustomerID(Long customerId) {
        return orderCrudRepository.findAllByCustomerId(customerId).stream()
                .map(OrderMapper::mapToDomain)
                .collect(Collectors.toList());
    }
}
