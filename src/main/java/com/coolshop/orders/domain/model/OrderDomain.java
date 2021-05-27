package com.coolshop.orders.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class OrderDomain {
    private final Long id;
    private final Long customerId;
    private final int discount;
    private final List<Long> itemIds;
}
