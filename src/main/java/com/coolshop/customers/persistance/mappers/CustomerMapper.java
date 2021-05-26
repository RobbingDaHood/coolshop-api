package com.coolshop.customers.persistance.mappers;

import com.coolshop.customers.domain.model.CustomerDomain;
import com.coolshop.customers.persistance.entities.CustomerEntity;

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
