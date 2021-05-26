package com.example.coolshop.customer.persistance.mappers;

import com.example.coolshop.customer.domain.model.CustomerDomain;
import com.example.coolshop.customer.persistance.entities.CustomerEntity;

public class CustomerMapper {

    public static CustomerEntity mapFromDomain(CustomerDomain customerDomain) {
        return CustomerEntity.builder()
                .fullName(customerDomain.getFullName())
                .build();
    }

    public static CustomerDomain mapToDomain(CustomerEntity customerEntity) {
        return CustomerDomain.builder()
                .id(customerEntity.getId())
                .fullName(customerEntity.getFullName())
                .build();
    }

}
