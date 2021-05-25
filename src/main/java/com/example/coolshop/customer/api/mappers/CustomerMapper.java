package com.example.coolshop.customer.api.mappers;

import com.example.coolshop.customer.api.representation.Customer;

public class CustomerMapper {

    public static Customer mapFromDomain(com.example.coolshop.customer.domain.model.Customer customer) {
        return Customer.builder()
                .id(customer.getId())
                .build();
    }

}
