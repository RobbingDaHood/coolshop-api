package com.example.coolshop.customer.persistance;

import com.example.coolshop.customer.persistance.entities.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerCrudRepository extends CrudRepository<CustomerEntity, Long> {
}
