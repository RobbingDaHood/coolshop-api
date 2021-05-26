package com.example.coolshop.orders.persistance.mappers;

import com.example.coolshop.orders.domain.model.OrderDomain;
import com.example.coolshop.orders.persistance.entities.OrderEntity;

public class OrderMapper {

    public static OrderEntity mapFromDomain(OrderDomain orderDomain) {
        return OrderEntity.builder()
                .customerId(orderDomain.getCustomerId())
                .discount(orderDomain.getDiscount())
                .itemIds(orderDomain.getItemIds())
                .build();
    }

    public static OrderDomain mapToDomain(OrderEntity orderEntity) {
        return OrderDomain.builder()
                .id(orderEntity.getId())
                .customerId(orderEntity.getCustomerId())
                .discount(orderEntity.getDiscount())
                .itemIds(orderEntity.getItemIds())
                .build();
    }
}
