package com.example.coolshop.orders.domain.exceptions;

import lombok.Getter;

@Getter
public class CustomerDoesNotExistException extends RuntimeException {
    private Long customerId;

    public CustomerDoesNotExistException(Long customerId) {
        super("Customer " + customerId + " does not exist.");
        this.customerId = customerId;
    }
}