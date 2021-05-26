package com.example.coolshop.integrationtest.stepdef.models;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
public class OrderIntegrationTest {
    private Long id;
    private Long customerId;
    private int discount;
    private List<Long> itemIds;
}
