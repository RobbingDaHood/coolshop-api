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
    private Long id;
    private Long customerId;
    private int discount;
    private List<Long> itemIds;
}
