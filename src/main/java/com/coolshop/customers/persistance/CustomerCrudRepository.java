package com.coolshop.customers.persistance;

import com.coolshop.customers.persistance.entities.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerCrudRepository extends CrudRepository<CustomerEntity, Long> {
}
