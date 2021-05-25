package com.example.coolshop.customer.api.representation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class CustomerRepresentation {
    private String id;
    private String fullName;
}
