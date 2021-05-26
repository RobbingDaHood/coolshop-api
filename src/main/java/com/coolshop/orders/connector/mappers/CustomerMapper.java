package com.coolshop.orders.connector.mappers;


import com.coolshop.customers.domain.model.CustomerDomain;

public class CustomerMapper {
    public static com.coolshop.orders.domain.model.CustomerDomain mapToDomain(CustomerDomain customerRepresentation) {
        return com.coolshop.orders.domain.model.CustomerDomain.builder().build();
    }
}
