package com.example.coolshop.orders.api.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerDoesNotExistExceptionRest {
    private final String exceptionName = "CustomerDoesNotExistException";
    private Long id;
    private String message;
}
