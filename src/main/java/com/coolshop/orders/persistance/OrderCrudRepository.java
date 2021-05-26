package com.coolshop.orders.persistance;

import com.coolshop.orders.persistance.entities.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderCrudRepository extends CrudRepository<OrderEntity, Long> {
}
