package com.example.coolshop.customer.api.mappers;

import com.example.coolshop.customer.api.representation.CustomerRepresentation;
import com.example.coolshop.customer.domain.model.CustomerDomain;

public class CustomerMapper {

    public static CustomerRepresentation mapFromDomain(CustomerDomain customerDomain) {
        return CustomerRepresentation.builder()
                .id(customerDomain.getId())
                .build();
    }

    public static CustomerDomain mapToDomain(CustomerRepresentation customerRepresentation) {
        return CustomerDomain.builder()
                .fullName(customerRepresentation.getFullName())
                .build();
    }

}
