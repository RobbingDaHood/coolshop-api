package com.example.coolshop.orders.connector.mappers;


import com.example.coolshop.orders.domain.model.CustomerDomain;

public class CustomerMapper {
    public static CustomerDomain mapToDomain(com.example.coolshop.customer.domain.model.CustomerDomain customerRepresentation) {
        return CustomerDomain.builder().build();
    }
}
