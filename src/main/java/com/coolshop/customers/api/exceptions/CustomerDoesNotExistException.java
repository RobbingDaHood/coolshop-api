package com.coolshop.customers.api.exceptions;

import lombok.Getter;

@Getter
public class CustomerDoesNotExistException extends RuntimeException {
    private final Long customerId;

    public CustomerDoesNotExistException(Long customerId) {
        super("Customer " + customerId + " does not exist.");
        this.customerId = customerId;
    }
}
