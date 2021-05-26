package com.coolshop.customers.api.mappers;

import com.coolshop.customers.api.representation.CustomerRepresentation;
import com.coolshop.customers.domain.model.CustomerDomain;

public class CustomerMapper {

    public static CustomerRepresentation mapFromDomain(CustomerDomain customerDomain) {
        return CustomerRepresentation.builder()
                .id(customerDomain.getId())
                .fullName(customerDomain.getFullName())
                .build();
    }

    public static CustomerDomain mapToDomain(CustomerRepresentation customerRepresentation) {
        return CustomerDomain.builder()
                .fullName(customerRepresentation.getFullName())
                .build();
    }

}
