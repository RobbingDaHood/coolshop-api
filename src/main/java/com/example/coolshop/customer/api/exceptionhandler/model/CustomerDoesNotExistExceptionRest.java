package com.example.coolshop.customer.api.exceptionhandler.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerDoesNotExistExceptionRest {
    private final String exceptionName = "CustomerDoesNotExistException";
    private Long customerId;
    private String message;
}
