package com.coolshop.orders.api.representation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class OrderRepresentation {
    private final Long id;
    @NotBlank(message = "Customer id is mandatory")
    private final Long customerId;
    @NotBlank(message = "Discount is mandatory")
    private final int discount;
    @NotBlank(message = "Item ids is mandatory")
    private final List<Long> itemIds;
}
