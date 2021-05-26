package com.coolshop.orders.api.mappers;

import com.coolshop.orders.api.representation.OrderRepresentation;
import com.coolshop.orders.domain.model.OrderDomain;

public class OrderMapper {

    public static OrderRepresentation mapFromDomain(OrderDomain orderDomain) {
        return OrderRepresentation.builder()
                .id(orderDomain.getId())
                .customerId(orderDomain.getCustomerId())
                .discount(orderDomain.getDiscount())
                .itemIds(orderDomain.getItemIds())
                .build();
    }

    public static OrderDomain mapToDomain(OrderRepresentation orderRepresentation) {
        return OrderDomain.builder()
                .id(orderRepresentation.getId())
                .customerId(orderRepresentation.getCustomerId())
                .discount(orderRepresentation.getDiscount())
                .itemIds(orderRepresentation.getItemIds())
                .build();
    }
}
