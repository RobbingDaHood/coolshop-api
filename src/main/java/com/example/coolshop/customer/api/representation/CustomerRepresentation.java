package com.example.coolshop.customer.api.representation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class CustomerRepresentation {
    private String id;
    private String fullName;
}
