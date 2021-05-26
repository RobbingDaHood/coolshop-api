package com.example.coolshop.orders.api.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderDoesNotExistExceptionRest {
    private final String exceptionName = "OrderDoesNotExistException";
    private Long id;
    private String message;
}
