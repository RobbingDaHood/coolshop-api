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
    private Long id;
    @NotBlank(message = "Customer id is mandatory")
    private Long customerId;
    @NotBlank(message = "Discount is mandatory")
    private int discount;
    @NotBlank(message = "Item ids is mandatory")
    private List<Long> itemIds;
}
