package com.coolshop.orders.persistance;

import com.coolshop.orders.persistance.entities.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface OrderCrudRepository extends CrudRepository<OrderEntity, Long>, Repository<OrderEntity, Long> {
    List<OrderEntity> findAllByCustomerId(Long customerId);
}
