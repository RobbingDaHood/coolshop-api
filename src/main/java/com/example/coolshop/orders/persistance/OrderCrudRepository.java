package com.example.coolshop.orders.persistance;

import com.example.coolshop.orders.persistance.entities.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderCrudRepository extends CrudRepository<OrderEntity, Long> {
}
