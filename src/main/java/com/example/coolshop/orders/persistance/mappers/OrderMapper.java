package com.example.coolshop.orders.persistance.mappers;

import com.example.coolshop.orders.domain.model.OrderDomain;
import com.example.coolshop.orders.persistance.entities.OrderEntity;

import java.util.Arrays;
import java.util.stream.Collectors;

public class OrderMapper {
    private static final String delimiter = ";";

    public static OrderEntity mapFromDomain(OrderDomain orderDomain) {
        return OrderEntity.builder()
                .customerId(orderDomain.getCustomerId())
                .discount(orderDomain.getDiscount())
                .itemIds(orderDomain.getItemIds().stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(delimiter, "", "")))
                .build();
    }

    public static OrderDomain mapToDomain(OrderEntity orderEntity) {
        return OrderDomain.builder()
                .id(orderEntity.getId())
                .customerId(orderEntity.getCustomerId())
                .discount(orderEntity.getDiscount())
                .itemIds(Arrays.stream(orderEntity.getItemIds().split(delimiter))
                        .map(Long::valueOf)
                        .collect(Collectors.toList()))
                .build();
    }
}
