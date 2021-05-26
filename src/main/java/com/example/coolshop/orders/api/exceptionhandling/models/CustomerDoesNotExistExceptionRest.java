package com.example.coolshop.orders.api.exceptionhandling.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerDoesNotExistExceptionRest {
    private final String exceptionName = "CustomerDoesNotExistException";
    private Long customerId;
    private String message;
}
